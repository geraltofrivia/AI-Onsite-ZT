"""
train.py

LoRA finetuning of a decoder-only code LLM to classify whether two Java
functions are functionally equivalent, framed as instruction-following
("Answer: Yes" / "Answer: No").

Usage:
    python train.py \
        --train_file data/train.jsonl \
        --val_file data/val.jsonl \
        --base_model codellama/CodeLlama-7b-hf \
        --output_dir ./clone-detector-lora \
        --epochs 3 \
        --batch_size 4

Requires: transformers, peft, trl, bitsandbytes, datasets, accelerate
"""

import argparse

import torch
from datasets import load_dataset
from peft import LoraConfig, get_peft_model, prepare_model_for_kbit_training
from transformers import (
    AutoModelForCausalLM,
    AutoTokenizer,
    BitsAndBytesConfig,
)
from trl import SFTTrainer, SFTConfig, DataCollatorForCompletionOnlyLM


def parse_args():
    ap = argparse.ArgumentParser()
    ap.add_argument("--train_file", required=True)
    ap.add_argument("--val_file", required=True)
    ap.add_argument("--base_model", default="codellama/CodeLlama-7b-hf")
    ap.add_argument("--output_dir", default="./clone-detector-lora")
    ap.add_argument("--epochs", type=int, default=3)
    ap.add_argument("--batch_size", type=int, default=4)
    ap.add_argument("--grad_accum_steps", type=int, default=4)
    ap.add_argument("--learning_rate", type=float, default=2e-4)
    ap.add_argument("--max_seq_length", type=int, default=1536)
    ap.add_argument("--lora_r", type=int, default=16)
    ap.add_argument("--lora_alpha", type=int, default=32)
    ap.add_argument("--lora_dropout", type=float, default=0.05)
    ap.add_argument("--use_4bit", action="store_true", default=True)
    ap.add_argument("--seed", type=int, default=42)
    return ap.parse_args()


def formatting_func(example):
    """
    Combines prompt + completion into a single training string.
    The completion (" Yes" / " No") must be the exact suffix so the
    completion-only data collator can mask the prompt tokens from the loss.
    """
    return example["prompt"] + example["completion"]


def main():
    args = parse_args()

    # ---- 1. Load data ----
    train_ds = load_dataset("json", data_files=args.train_file, split="train")
    val_ds = load_dataset("json", data_files=args.val_file, split="train")

    # ---- 2. Tokenizer ----
    tokenizer = AutoTokenizer.from_pretrained(args.base_model)
    if tokenizer.pad_token is None:
        tokenizer.pad_token = tokenizer.eos_token
    tokenizer.padding_side = "right"

    # ---- 3. Load base model in 4-bit (QLoRA) ----
    bnb_config = BitsAndBytesConfig(
        load_in_4bit=args.use_4bit,
        bnb_4bit_quant_type="nf4",
        bnb_4bit_compute_dtype=torch.bfloat16,
        bnb_4bit_use_double_quant=True,
    )

    model = AutoModelForCausalLM.from_pretrained(
        args.base_model,
        quantization_config=bnb_config if args.use_4bit else None,
        device_map="auto",
        torch_dtype=torch.bfloat16,
    )
    model.config.use_cache = False  # required for gradient checkpointing

    if args.use_4bit:
        model = prepare_model_for_kbit_training(model)

    # ---- 4. Attach LoRA adapters ----
    # target_modules below are correct for Llama-family architectures
    # (CodeLlama, Llama-2/3). Adjust if using a different base model family
    # (e.g. for Qwen: ["q_proj", "k_proj", "v_proj", "o_proj"]).
    lora_config = LoraConfig(
        r=args.lora_r,
        lora_alpha=args.lora_alpha,
        lora_dropout=args.lora_dropout,
        target_modules=["q_proj", "k_proj", "v_proj", "o_proj", "gate_proj", "up_proj", "down_proj"],
        bias="none",
        task_type="CAUSAL_LM",
    )
    model = get_peft_model(model, lora_config)
    model.print_trainable_parameters()

    # ---- 5. Completion-only loss masking ----
    # This ensures the loss is only computed on "Yes"/"No" tokens, not on the
    # (long) prompt containing both function bodies -- much more sample-efficient.
    response_template = "\nAnswer:"
    collator = DataCollatorForCompletionOnlyLM(
        response_template=response_template,
        tokenizer=tokenizer,
    )

    # ---- 6. Training config ----
    sft_config = SFTConfig(
        output_dir=args.output_dir,
        num_train_epochs=args.epochs,
        per_device_train_batch_size=args.batch_size,
        per_device_eval_batch_size=args.batch_size,
        gradient_accumulation_steps=args.grad_accum_steps,
        gradient_checkpointing=True,
        learning_rate=args.learning_rate,
        lr_scheduler_type="cosine",
        warmup_ratio=0.03,
        logging_steps=10,
        eval_strategy="steps",
        eval_steps=50,
        save_strategy="steps",
        save_steps=50,
        save_total_limit=2,
        load_best_model_at_end=True,
        metric_for_best_model="eval_loss",
        bf16=True,
        max_seq_length=args.max_seq_length,
        packing=False,  # packing is incompatible with completion-only masking
        seed=args.seed,
        report_to="none",  # set to "wandb" if you want experiment tracking
    )

    trainer = SFTTrainer(
        model=model,
        args=sft_config,
        train_dataset=train_ds,
        eval_dataset=val_ds,
        formatting_func=formatting_func,
        data_collator=collator,
        processing_class=tokenizer,
    )

    trainer.train()

    # ---- 7. Save final adapter + tokenizer ----
    trainer.save_model(args.output_dir)
    tokenizer.save_pretrained(args.output_dir)
    print(f"Training complete. LoRA adapter saved to {args.output_dir}")


if __name__ == "__main__":
    main()