from typing import Any, Dict, List, Tuple
import numpy as np

AVAILABLE = False
try:
    import torch
    from peft import LoraConfig, get_peft_model
    from prepare_prompt import build_example
    from transformers import AutoModelForCausalLM, AutoTokenizer, Trainer, TrainingArguments
    AVAILABLE = True
except ImportError:
    torch = None
    LoraConfig = None
    get_peft_model = None
    build_example = None
    AutoModelForCausalLM = None
    AutoTokenizer = None
    Trainer = None
    TrainingArguments = None


VALID_LABELS = {"Yes": 1, "Maybe": 1, "No": 0}


def is_model3_available() -> bool:
    return AVAILABLE and torch is not None and AutoModelForCausalLM is not None


def _build_dataset(
    pairs: List[Any],
    tokenizer: Any,
    max_length: int,
) -> Any:
    prompts = []
    labels = []
    for pair in pairs:
        example = build_example(pair.left_code, pair.right_code, VALID_LABELS[pair.label])
        prompts.append(example["prompt"] + example["completion"])
        labels.append(VALID_LABELS[pair.label])

    encodings = tokenizer(prompts, truncation=True, padding="max_length", max_length=max_length)
    input_ids = encodings["input_ids"]

    label_ids = []
    for prompt_text, ids in zip(prompts, input_ids):
        completion_ids = tokenizer(example["completion"], truncation=True, max_length=max_length)["input_ids"]
        prompt_len = len(ids) - len(completion_ids)
        labels_for_example = ids.copy()
        for i in range(prompt_len):
            labels_for_example[i] = -100
        label_ids.append(labels_for_example)

    class ClmDataset(torch.utils.data.Dataset):
        def __init__(self, encodings: Dict[str, List[Any]], labels: List[List[int]]):
            self.encodings = encodings
            self.labels = labels

        def __len__(self) -> int:
            return len(self.labels)

        def __getitem__(self, idx: int) -> Dict[str, Any]:
            item = {k: torch.tensor(v[idx]) for k, v in self.encodings.items()}
            item["labels"] = torch.tensor(self.labels[idx], dtype=torch.long)
            return item

    return ClmDataset(encodings, label_ids)


def train_icl_llm(
    train_pairs: List[Any],
    eval_pairs: List[Any],
    model_name: str = "codellama/CodeLlama-7b-hf",
    output_dir: str = "./model3_output",
    epochs: int = 3,
    batch_size: int = 8,
    learning_rate: float = 2e-5,
    max_length: int = 512,
) -> Tuple[Any, Any]:
    if not is_model3_available():
        raise RuntimeError("model3 requires transformers, torch, and peft")

    tokenizer = AutoTokenizer.from_pretrained(model_name)
    model = AutoModelForCausalLM.from_pretrained(model_name, device_map="auto")

    lora_config = LoraConfig(
        r=16,
        lora_alpha=32,
        target_modules=["q_proj", "v_proj"],
        lora_dropout=0.05,
        task_type="CAUSAL_LM",
    )
    model = get_peft_model(model, lora_config)

    train_dataset = _build_dataset(train_pairs, tokenizer, max_length)
    eval_dataset = _build_dataset(eval_pairs, tokenizer, max_length)

    training_args = TrainingArguments(
        output_dir=output_dir,
        per_device_train_batch_size=batch_size,
        per_device_eval_batch_size=batch_size,
        learning_rate=learning_rate,
        num_train_epochs=epochs,
        evaluation_strategy="epoch",
        save_strategy="epoch",
        logging_strategy="epoch",
        load_best_model_at_end=True,
        metric_for_best_model="loss",
        fp16=torch.cuda.is_available(),
    )

    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=train_dataset,
        eval_dataset=eval_dataset,
    )
    trainer.train()
    return model, tokenizer


def predict_icl_llm(
    model: Any,
    tokenizer: Any,
    pairs: List[Any],
    max_length: int = 512,
) -> np.ndarray:
    if not is_model3_available():
        raise RuntimeError("model3 requires transformers, torch, and peft")

    prompts = []
    for pair in pairs:
        example = build_example(pair.left_code, pair.right_code, VALID_LABELS[pair.label])
        prompts.append(example["prompt"])

    encodings = tokenizer(prompts, truncation=True, padding=True, max_length=max_length, return_tensors="pt")
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model.to(device)
    input_tensors = {k: v.to(device) for k, v in encodings.items()}

    outputs = model.generate(**input_tensors, max_new_tokens=32)
    probs = []
    for generated in outputs:
        text = tokenizer.decode(generated, skip_special_tokens=True).strip()
        if text.lower().endswith("yes"):
            probs.append(1.0)
        else:
            probs.append(0.0)

    return np.array(probs, dtype=float)
