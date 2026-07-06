import os
import csv
from pathlib import Path
from typing import Dict, List, Optional, Any
from dataclasses import dataclass
import numpy as np

@dataclass
class CodePair:
    """Represents a pair of code snippets with their embeddings and label."""
    batch_id: str
    pair_id: int
    left_code: str
    right_code: str
    left_embedding: Optional[Any]  # np.ndarray if numpy available, else None
    right_embedding: Optional[Any]  # np.ndarray if numpy available, else None
    label: str  # "Yes", "No", or "Not Sure"
    
    def __repr__(self):
        return (f"CodePair(batch_id={self.batch_id}, pair_id={self.pair_id}, "
                f"label={self.label})")


class CodeSimilarityDataLoader:
    """
    Data loader for code similarity dataset.
    
    Loads code pairs with embeddings from Data/ folder and their corresponding
    labels from Labels/ folder. Pairs data with labels based on line numbers.
    """
    
    def __init__(self, data_root: str, labels_root: str):
        """
        Initialize the data loader.
        
        Args:
            data_root: Path to the Data/ folder containing result_batch subdirectories
            labels_root: Path to the Labels/ folder containing label CSV files
        """
        self.data_root = Path(data_root)
        self.labels_root = Path(labels_root)
        self.code_pairs: List[CodePair] = []
        self.labels_map: Dict[int, str] = {}  # Maps batch_label_id to similarity label
        self.ignored_data: List[Dict[str, Any]] = []
        self.total_pair_dirs: int = 0
        
        self._load_labels()
        self._load_data()
    
    def _load_labels(self) -> None:
        """Load all label CSVs into a mapping."""
        for label_file in sorted(self.labels_root.glob("label_*.csv")):
            # Extract batch number from filename (e.g., "label_1.csv" -> 1)
            batch_num = int(label_file.stem.split("_")[1])
            
            with open(label_file, 'r') as f:
                reader = csv.DictReader(f)
                for row in reader:
                    line_number = int(row['line_number'])
                    similarity = row['similar'].strip()
                    
                    # Create unique key: batch_id + line_number
                    key = batch_num * 100000 + line_number
                    self.labels_map[key] = similarity
    
    def _load_data(self) -> None:
        """Load all code pairs from the data directories."""
        # Iterate through all result_batch directories
        for batch_dir in sorted(self.data_root.glob("result_batch*")):
            batch_id = batch_dir.name  # e.g., "result_batch1"
            batch_num = int(batch_id.replace("result_batch", ""))
            
            # Iterate through all numbered subdirectories in the batch
            for pair_dir in sorted(batch_dir.iterdir(), 
                                   key=lambda x: int(x.name) if x.name.isdigit() else float('inf')):
                if not pair_dir.is_dir() or not pair_dir.name.isdigit():
                    continue
                
                self.total_pair_dirs += 1
                pair_id = int(pair_dir.name)
                
                try:
                    # Load code files
                    left_code = self._load_code_file(pair_dir / "LeftRawCode.java")
                    right_code = self._load_code_file(pair_dir / "RightRawCode.java")
                    
                    # Load embedding files (only if numpy is available)
                    left_embedding = np.load(pair_dir / "LeftRawCode.npy")
                    right_embedding = np.load(pair_dir / "RightRawCode.npy")
                    
                    # Get label from labels map
                    key = batch_num * 100000 + pair_id
                    label = self.labels_map.get(key, "Unknown")

                    # Create CodePair object
                    code_pair = CodePair(
                        batch_id=batch_id,
                        pair_id=pair_id,
                        left_code=left_code,
                        right_code=right_code,
                        left_embedding=left_embedding,
                        right_embedding=right_embedding,
                        label=label
                    )

                    self.code_pairs.append(code_pair)
                except RuntimeError as runtime_error:
                    self.ignored_data.append({
                        "batch_id": batch_id,
                        "pair_id": pair_id,
                        "reason": str(runtime_error),
                        "path": str(pair_dir)
                    })
                except OSError as os_error:
                    self.ignored_data.append({
                        "batch_id": batch_id,
                        "pair_id": pair_id,
                        "reason": str(os_error),
                        "path": str(pair_dir)
                    })
                except Exception as e:
                    print(f"Error loading data from {pair_dir}: {e}")
    
    @staticmethod
    def _load_code_file(filepath: Path) -> str:
        """Load and return the contents of a code file."""
        try:
            with open(filepath, 'r', encoding='utf-8') as f:
                return f.read()
        except UnicodeDecodeError:
            # Fallback to latin-1 encoding if UTF-8 fails
            with open(filepath, 'r', encoding='latin-1') as f:
                return f.read()
    
    def get_all_pairs(self) -> List[CodePair]:
        """Get all loaded code pairs."""
        return self.code_pairs
    
    def get_pairs_by_batch(self, batch_id: str) -> List[CodePair]:
        """Get all code pairs from a specific batch."""
        return [pair for pair in self.code_pairs if pair.batch_id == batch_id]
    
    def get_pairs_by_label(self, label: str) -> List[CodePair]:
        """Get all code pairs with a specific label."""
        return [pair for pair in self.code_pairs if pair.label == label]
    
    def get_statistics(self) -> Dict:
        """Get statistics about the loaded dataset."""
        stats = {
            "total_pair_dirs": self.total_pair_dirs,
            "total_pairs": len(self.code_pairs),
            "ignored_pairs": len(self.ignored_data),
            "by_label": {},
            "by_batch": {},
            "embeddings_shape": None,
        }
        
        # Count by label
        for label in ["Yes", "No", "Not Sure", "Unknown"]:
            count = len(self.get_pairs_by_label(label))
            if count > 0:
                stats["by_label"][label] = count
        
        # Count by batch
        batch_ids = set(pair.batch_id for pair in self.code_pairs)
        for batch_id in sorted(batch_ids):
            count = len(self.get_pairs_by_batch(batch_id))
            stats["by_batch"][batch_id] = count
        
        # Get embedding shape
        if self.code_pairs and self.code_pairs[0].left_embedding is not None:
            stats["embeddings_shape"] = self.code_pairs[0].left_embedding.shape
        
        return stats
    
    def get_ignored_data(self) -> List[Dict[str, Any]]:
        """Get the list of ignored data points with reasons."""
        return self.ignored_data
    
    def __len__(self) -> int:
        """Get the number of loaded code pairs."""
        return len(self.code_pairs)
    
    def __getitem__(self, index: int) -> CodePair:
        """Get a code pair by index."""
        return self.code_pairs[index]


# Example usage
"""
Dataset Statistics:
Total pair directories: 461
Total loaded pairs: 432
Ignored pairs: 29
Embeddings shape: (768,)

Label distribution:
  Yes: 228
  No: 183
  Not Sure: 17
"""
if __name__ == "__main__":
    # Initialize data loader
    data_loader = CodeSimilarityDataLoader(
        data_root="/Users/ztian/Documents/GitHub/AI-Onsite-ZT/Data",
        labels_root="/Users/ztian/Documents/GitHub/AI-Onsite-ZT/Labels"
    )
    
    # Print statistics
    print("Dataset Statistics:")
    stats = data_loader.get_statistics()
    print(f"Total pair directories: {stats['total_pair_dirs']}")
    print(f"Total loaded pairs: {stats['total_pairs']}")
    print(f"Ignored pairs: {stats['ignored_pairs']}")
    if stats['embeddings_shape']:
        print(f"Embeddings shape: {stats['embeddings_shape']}")
    print("\nLabel distribution:")
    for label, count in stats["by_label"].items():
        print(f"  {label}: {count}")
    print("\nBatch distribution:")
    for batch, count in stats["by_batch"].items():
        print(f"  {batch}: {count}")

