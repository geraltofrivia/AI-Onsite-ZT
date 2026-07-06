import argparse
import random
from pathlib import Path
from typing import Any, Dict, List, Tuple

import numpy as np

from data_loader import CodeSimilarityDataLoader
from model1 import train_lightgbm, pairwise_features

try:
    from model2 import train_llm, predict_llm, is_model2_available
except ImportError:
    train_llm = None
    predict_llm = None
    is_model2_available = lambda: False

try:
    from model3 import train_icl_llm, predict_icl_llm, is_model3_available
except ImportError:
    train_icl_llm = None
    predict_icl_llm = None
    is_model3_available = lambda: False

VALID_LABELS = {"Yes": 1, "No": 0}
MODEL_CHOICES = ["model1", "model2", "model3"]


def batch_split_pairs(
    pairs: List[Any],
    test_size: float = 0.2,
    random_state: int = 42,
) -> Tuple[List[Any], List[Any]]:
    """Split code pairs into train/test sets by batch id."""
    batch_ids = sorted({pair.batch_id for pair in pairs})
    if not batch_ids:
        return [], []

    rng = random.Random(random_state)
    n_test = max(1, int(round(len(batch_ids) * test_size)))
    test_batch_ids = set(rng.sample(batch_ids, n_test))

    train_pairs = [pair for pair in pairs if pair.batch_id not in test_batch_ids]
    test_pairs = [pair for pair in pairs if pair.batch_id in test_batch_ids]

    return train_pairs, test_pairs


def build_feature_matrix(
    pairs: List[Any],
) -> Tuple[np.ndarray, np.ndarray, List[str]]:
    """Convert code pairs into a numeric feature matrix and binary labels."""
    rows: List[List[float]] = []
    labels: List[int] = []
    feature_names: List[str] = []

    for pair in pairs:
        if pair.label not in VALID_LABELS:
            continue

        row_dict = pairwise_features(pair.left_embedding, pair.right_embedding)
        if not feature_names:
            feature_names = list(row_dict.keys())

        rows.append([float(row_dict[name]) for name in feature_names])
        labels.append(VALID_LABELS[pair.label])

    return np.array(rows, dtype=float), np.array(labels, dtype=int), feature_names


def print_dataset_summary(
    train_pairs: List[Any],
    test_pairs: List[Any],
    stats: Dict[str, Any],
) -> None:
    print("Dataset summary")
    print(f"  total pair directories: {stats.get('total_pair_dirs', '?')}")
    print(f"  total loaded pairs: {stats.get('total_pairs', '?')}")
    print(f"  ignored pairs: {stats.get('ignored_pairs', '?')}")
    print(f"  valid labeled pairs: {len(train_pairs) + len(test_pairs)}")
    print(f"  train batches: {len(set(pair.batch_id for pair in train_pairs))}")
    print(f"  test batches: {len(set(pair.batch_id for pair in test_pairs))}")
    print(f"  train pairs: {len(train_pairs)}")
    print(f"  test pairs: {len(test_pairs)}")


def evaluate_predictions(y_true: np.ndarray, y_prob: np.ndarray) -> Dict[str, float]:
    y_pred = (y_prob >= 0.5).astype(int)
    accuracy = float((y_pred == y_true).mean())
    precision = float((y_pred[y_true == 1] == 1).sum() / max(1, (y_pred == 1).sum()))
    recall = float((y_pred[y_true == 1] == 1).sum() / max(1, (y_true == 1).sum()))
    f1 = float(2 * precision * recall / max(1e-8, precision + recall))
    return {
        "accuracy": accuracy,
        "precision": precision,
        "recall": recall,
        "f1": f1,
    }


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Train and evaluate a code similarity model.")
    parser.add_argument("--model", choices=MODEL_CHOICES, default="model1", help="Which model to run.")
    parser.add_argument("--test-size", type=float, default=0.2, help="Fraction of batches to reserve for testing.")
    parser.add_argument("--llm-model-name", type=str, default="codellama/CodeLlama-7b-hf", help="Hugging Face model name for model2/model3.")
    parser.add_argument("--output-dir", type=str, default="./model_output", help="Output directory for model2/model3 artifacts.")
    parser.add_argument("--epochs", type=int, default=3, help="Number of epochs for model2/model3 training.")
    parser.add_argument("--batch-size", type=int, default=8, help="Batch size for model2/model3 training.")
    parser.add_argument("--learning-rate", type=float, default=2e-5, help="Learning rate for model2/model3 training.")
    parser.add_argument("--max-length", type=int, default=512, help="Maximum token length for model2/model3 inputs.")
    return parser.parse_args()


def main() -> None:
    args = parse_args()
    data_root = Path("Data")
    labels_root = Path("Labels")

    loader = CodeSimilarityDataLoader(data_root=data_root, labels_root=labels_root)
    all_pairs = [pair for pair in loader.get_all_pairs() if pair.label in VALID_LABELS]

    train_pairs, test_pairs = batch_split_pairs(all_pairs, test_size=args.test_size, random_state=42)
    stats = loader.get_statistics()
    print_dataset_summary(train_pairs, test_pairs, stats)

    y_test = np.array([VALID_LABELS[pair.label] for pair in test_pairs], dtype=int)

    if args.model == "model1":
        X_train, y_train, feature_names = build_feature_matrix(train_pairs)
        X_test, _, _ = build_feature_matrix(test_pairs)

        if X_train.size == 0 or X_test.size == 0:
            raise ValueError("Not enough valid data to train and evaluate the model.")

        model = train_lightgbm(
            X_train,
            y_train,
            X_test,
            y_test,
            params={
                "objective": "binary",
                "metric": ["auc", "binary_logloss"],
                "is_unbalance": True,
                "learning_rate": 0.05,
                "num_leaves": 31,
                "min_data_in_leaf": 20,
                "verbosity": -1,
            },
            num_boost_round=1000,
            early_stopping_rounds=50,
        )

        y_prob = model.predict(X_test, num_iteration=model.best_iteration or None)
        metrics = evaluate_predictions(y_test, y_prob)

        print("\nEvaluation")
        for name, value in metrics.items():
            print(f"  {name}: {value:.4f}")

        print("\nFeature importances")
        importances = model.feature_importance(importance_type="gain")
        for name, importance in sorted(zip(feature_names, importances), key=lambda x: x[1], reverse=True):
            print(f"  {name}: {importance:.2f}")

    elif args.model == "model2":
        if train_llm is None or predict_llm is None or not is_model2_available():
            raise RuntimeError(
                "Model2 is not available. Install transformers and torch, or update model2.py to provide a valid LLM interface."
            )

        llm_model = train_llm(
            train_pairs,
            test_pairs,
            model_name=args.llm_model_name,
            output_dir=args.output_dir,
            epochs=args.epochs,
            batch_size=args.batch_size,
            learning_rate=args.learning_rate,
            max_length=args.max_length,
        )

        y_prob = predict_llm(llm_model, test_pairs, max_length=args.max_length)
        metrics = evaluate_predictions(y_test, y_prob)

        print("\nEvaluation")
        for name, value in metrics.items():
            print(f"  {name}: {value:.4f}")

    elif args.model == "model3":
        if train_icl_llm is None or predict_icl_llm is None or not is_model3_available():
            raise RuntimeError(
                "Model3 is not available. Install required model3 dependencies or update model3.py with a valid interface."
            )

        model, tokenizer = train_icl_llm(
            train_pairs,
            test_pairs,
            model_name=args.llm_model_name,
            output_dir=args.output_dir,
            epochs=args.epochs,
            batch_size=args.batch_size,
            learning_rate=args.learning_rate,
            max_length=args.max_length,
        )

        y_prob = predict_icl_llm(model, tokenizer, test_pairs, max_length=args.max_length)
        metrics = evaluate_predictions(y_test, y_prob)

        print("\nEvaluation")
        for name, value in metrics.items():
            print(f"  {name}: {value:.4f}")

    else:
        raise ValueError(f"Unknown model selection: {args.model}")


if __name__ == "__main__":
    main()
