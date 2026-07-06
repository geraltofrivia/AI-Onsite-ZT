import lightgbm as lgb
import numpy as np
from typing import Dict, Optional

def pairwise_features(e1: np.ndarray, e2: np.ndarray) -> dict:
    diff = e1 - e2
    abs_diff = np.abs(diff)
    prod = e1 * e2

    norm1 = np.linalg.norm(e1)
    norm2 = np.linalg.norm(e2)
    dot = np.dot(e1, e2)

    cosine = dot / (norm1 * norm2 + 1e-8)

    return {
        "cosine_sim": cosine,
        "euclidean_dist": np.linalg.norm(diff),
        "manhattan_dist": np.sum(abs_diff),
        "chebyshev_dist": np.max(abs_diff),
        "dot_product": dot,
        "norm1": norm1,
        "norm2": norm2,
        "norm_diff": abs(norm1 - norm2),
        "absdiff_mean": abs_diff.mean(),
        "absdiff_std": abs_diff.std(),
        "absdiff_max": abs_diff.max(),
        "prod_sum": prod.sum(),  # == dot_product, keep only one in practice
    }

def train_lightgbm(
    X_train: np.ndarray,
    y_train: np.ndarray,
    X_valid: np.ndarray,
    y_valid: np.ndarray,
    params: Optional[Dict] = None,
    num_boost_round: int = 1000,
    early_stopping_rounds: int = 50,
) -> lgb.Booster:
    """Train a LightGBM binary classification model."""
    if params is None:
        params = {
            "objective": "binary",
            "metric": ["auc", "binary_logloss"],
            "is_unbalance": True,
            "learning_rate": 0.05,
            "num_leaves": 31,
            "min_data_in_leaf": 20,
            "verbosity": -1,
        }

    train_data = lgb.Dataset(X_train, label=y_train)
    valid_data = lgb.Dataset(X_valid, label=y_valid, reference=train_data)

    model = lgb.train(
        params,
        train_data,
        valid_sets=[valid_data],
        num_boost_round=num_boost_round,
        callbacks=[lgb.early_stopping(early_stopping_rounds)],
    )

    return model
