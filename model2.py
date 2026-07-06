from transformers import AutoTokenizer, AutoModelForSequenceClassification, TrainingArguments, Trainer

model_name = "microsoft/unixcoder-base"  # or codebert-base, graphcodebert-base
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForSequenceClassification.from_pretrained(model_name, num_labels=2)

def preprocess(example):
    return tokenizer(
        example["func1"], example["func2"],
        truncation=True, max_length=512, padding="max_length"
    )

train_ds = raw_train_ds.map(preprocess, batched=True)

training_args = TrainingArguments(
    output_dir="./clone-detector",
    per_device_train_batch_size=8,
    learning_rate=2e-5,
    num_train_epochs=3,
    evaluation_strategy="epoch",
    fp16=True,
)

trainer = Trainer(model=model, args=training_args, train_dataset=train_ds, eval_dataset=val_ds)
trainer.train()