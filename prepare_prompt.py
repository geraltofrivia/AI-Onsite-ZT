"""
prepare_data.py

Converts a dataset of (func1, func2, label) triples into the instruction
format expected by train.py, and does a leakage-safe train/val/test split
(split by function identity, not by pair, so the same function never
appears in both train and test).

Expected input: a JSON or CSV file with columns:
    func1_id, func1_code, func2_id, func2_code, label   (label: 1 = same, 0 = different)

Output: train.jsonl, val.jsonl, test.jsonl in --output_dir, each line:
    {"prompt": "...", "completion": " Yes" | " No"}
"""

import argparse
import json
import random
from pathlib import Path

import pandas as pd
from sklearn.model_selection import train_test_split


PROMPT_TEMPLATE = """Are these two Java functions functionally equivalent? Answer with exactly one word: Yes or No.

Function 1:
```java
{func1}
```

Function 2:
```java
{func2}
```

Answer:"""


def build_example(func1: str, func2: str, label: int) -> dict:
    prompt = PROMPT_TEMPLATE.format(func1=func1.strip(), func2=func2.strip())
    completion = " Yes" if label == 1 else " No"
    return {"prompt": prompt, "completion": completion}


def split_by_function_identity(df: pd.DataFrame, test_size: float, val_size: float, seed: int):
    """
    Ensures no function (by id) appears in more than one split.
    This avoids leakage where func A is seen in train and also in test.
    """
    all_ids = pd.unique(df[["func1_id", "func2_id"]].values.ravel())
    train_ids, testval_ids = train_test_split(all_ids, test_size=(test_size + val_size), random_state=seed)
    val_ids, test_ids = train_test_split(
        testval_ids, test_size=test_size / (test_size + val_size), random_state=seed
    )

    train_ids, val_ids, test_ids = set(train_ids), set(val_ids), set(test_ids)

    def assign(row):
        ids = {row["func1_id"], row["func2_id"]}
        if ids <= train_ids:
            return "train"
        if ids <= val_ids:
            return "val"
        if ids <= test_ids:
            return "test"
        return "drop"  # pair straddles two splits -> drop to avoid leakage

    df = df.copy()
    df["split"] = df.apply(assign, axis=1)
    dropped = (df["split"] == "drop").sum()
    if dropped:
        print(f"Dropping {dropped} pairs that straddled split boundaries (leakage prevention).")
    return df[df["split"] != "drop"]


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--input", required=True, help="Path to input CSV or JSON file")
    ap.add_argument("--output_dir", required=True)
    ap.add_argument("--test_size", type=float, default=0.15)
    ap.add_argument("--val_size", type=float, default=0.15)
    ap.add_argument("--seed", type=int, default=42)
    ap.add_argument("--max_chars", type=int, default=6000, help="Truncate very long functions")
    args = ap.parse_args()

    random.seed(args.seed)

    in_path = Path(args.input)
    if in_path.suffix == ".json":
        df = pd.read_json(in_path)
    elif in_path.suffix == ".jsonl":
        df = pd.read_json(in_path, lines=True)
    else:
        df = pd.read_csv(in_path)

    required_cols = {"func1_id", "func1_code", "func2_id", "func2_code", "label"}
    missing = required_cols - set(df.columns)
    if missing:
        raise ValueError(f"Input file is missing required columns: {missing}")

    # Basic cleaning / truncation to keep prompts within context limits
    df["func1_code"] = df["func1_code"].str.slice(0, args.max_chars)
    df["func2_code"] = df["func2_code"].str.slice(0, args.max_chars)

    df = split_by_function_identity(df, args.test_size, args.val_size, args.seed)

    out_dir = Path(args.output_dir)
    out_dir.mkdir(parents=True, exist_ok=True)

    for split_name in ["train", "val", "test"]:
        split_df = df[df["split"] == split_name]
        out_path = out_dir / f"{split_name}.jsonl"
        with open(out_path, "w") as f:
            for _, row in split_df.iterrows():
                ex = build_example(row["func1_code"], row["func2_code"], int(row["label"]))
                f.write(json.dumps(ex) + "\n")
        pos = (split_df["label"] == 1).sum()
        print(f"{split_name}: {len(split_df)} pairs ({pos} positive, {len(split_df) - pos} negative) -> {out_path}")


if __name__ == "__main__":
    main()