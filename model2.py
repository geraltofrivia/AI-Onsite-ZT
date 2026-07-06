from typing import Any, Dict, List, Optional, Tuple

import numpy as np

AVAILABLE = False
try:
    import torch
    from torch import nn
    from torch.utils.data import Dataset
    from transformers import AutoModel, AutoTokenizer, Trainer, TrainingArguments
    AVAILABLE = True
except ImportError:
    torch = None
    nn = None
    Dataset = None
    AutoModel = None
    AutoTokenizer = None
    Trainer = None
    TrainingArguments = None

VALID_LABELS = {"Yes": 1, "Maybe": 1, "No": 0}


def is_model2_available() -> bool:
    return AVAILABLE and torch is not None and AutoModel is not None


def _build_embedding_features(pair: Any) -> np.ndarray:
    left_emb = np.asarray(pair.left_embedding, dtype=np.float32)
    right_emb = np.asarray(pair.right_embedding, dtype=np.float32)
    return np.concatenate([
        left_emb,
        right_emb,
        np.abs(left_emb - right_emb),
        left_emb * right_emb,
    ], axis=-1)


class JointCodeEmbeddingDataset(Dataset):
    def __init__(self, pairs: List[Any], tokenizer: Any, max_length: int):
        self.examples: List[Dict[str, Any]] = []
        self.embedding_features: List[np.ndarray] = []
        self.labels: List[int] = []

        for pair in pairs:
            if pair.label not in VALID_LABELS:
                continue
            if pair.left_embedding is None or pair.right_embedding is None:
                continue

            encoded = tokenizer(
                pair.left_code,
                pair.right_code,
                truncation=True,
                padding="max_length",
                max_length=max_length,
                return_tensors="np",
            )

            self.examples.append(encoded)
            self.embedding_features.append(_build_embedding_features(pair))
            self.labels.append(VALID_LABELS[pair.label])

        if not self.examples:
            raise ValueError("No examples were loaded for the joint code+embedding dataset.")

        self.embedding_features = np.stack(self.embedding_features, axis=0)
        self.labels = np.array(self.labels, dtype=np.int64)

    def __len__(self) -> int:
        return len(self.labels)

    def __getitem__(self, idx: int) -> Dict[str, Any]:
        encoded = self.examples[idx]
        return {
            "input_ids": torch.tensor(encoded["input_ids"], dtype=torch.long),
            "attention_mask": torch.tensor(encoded["attention_mask"], dtype=torch.long),
            "embedding_features": torch.tensor(self.embedding_features[idx], dtype=torch.float32),
            "labels": torch.tensor(self.labels[idx], dtype=torch.long),
        }


class CodeEmbeddingClassifier(nn.Module):
    def __init__(self, model_name: str, embedding_dim: int, hidden_size: int = 256):
        super().__init__()
        self.text_encoder = AutoModel.from_pretrained(model_name)
        self.embedding_projector = nn.Sequential(
            nn.Linear(embedding_dim, hidden_size),
            nn.ReLU(),
            nn.Dropout(0.1),
        )
        self.classifier = nn.Sequential(
            nn.Linear(self.text_encoder.config.hidden_size + hidden_size, hidden_size),
            nn.Tanh(),
            nn.Dropout(0.1),
            nn.Linear(hidden_size, 1),
        )

    def forward(
        self,
        input_ids: torch.Tensor,
        attention_mask: torch.Tensor,
        embedding_features: torch.Tensor,
        labels: Optional[torch.Tensor] = None,
    ) -> Dict[str, Any]:
        outputs = self.text_encoder(input_ids=input_ids, attention_mask=attention_mask, return_dict=True)
        text_repr = outputs.last_hidden_state[:, 0, :]
        embed_repr = self.embedding_projector(embedding_features)
        combined = torch.cat([text_repr, embed_repr], dim=-1)
        logits = self.classifier(combined).view(-1)

        loss = None
        if labels is not None:
            loss_fn = nn.BCEWithLogitsLoss()
            loss = loss_fn(logits, labels.float())

        result: Dict[str, Any] = {"logits": logits}
        if loss is not None:
            result["loss"] = loss
        return result


def train_llm(
    train_pairs: List[Any],
    eval_pairs: List[Any],
    model_name: str = "microsoft/unixcoder-base",
    output_dir: str = "./model2_output",
    epochs: int = 3,
    batch_size: int = 8,
    learning_rate: float = 2e-5,
    max_length: int = 512,
) -> Tuple[Any, Any]:
    if not is_model2_available():
        raise RuntimeError("model2 requires torch and transformers.")

    tokenizer = AutoTokenizer.from_pretrained(model_name)
    train_dataset = JointCodeEmbeddingDataset(train_pairs, tokenizer, max_length=max_length)
    eval_dataset = JointCodeEmbeddingDataset(eval_pairs, tokenizer, max_length=max_length)

    embedding_dim = int(train_dataset.embedding_features.shape[1])
    model = CodeEmbeddingClassifier(model_name=model_name, embedding_dim=embedding_dim)

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


def predict_llm(
    model: Any,
    tokenizer: Any,
    pairs: List[Any],
    max_length: int = 512,
) -> np.ndarray:
    if not is_model2_available():
        raise RuntimeError("model2 requires torch and transformers.")

    dataset = JointCodeEmbeddingDataset(pairs, tokenizer, max_length=max_length)
    loader = torch.utils.data.DataLoader(dataset, batch_size=32)
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model.to(device)
    model.eval()

    probabilities: List[float] = []
    with torch.no_grad():
        for batch in loader:
            input_ids = batch["input_ids"].to(device)
            attention_mask = batch["attention_mask"].to(device)
            embedding_features = batch["embedding_features"].to(device)
            outputs = model(
                input_ids=input_ids,
                attention_mask=attention_mask,
                embedding_features=embedding_features,
            )
            logits = outputs["logits"]
            probs = torch.sigmoid(logits)
            probabilities.extend(probs.cpu().numpy().tolist())

    return np.array(probabilities, dtype=float)
