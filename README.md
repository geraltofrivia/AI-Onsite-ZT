# AI Researcher Onsite Task

## Task

In this task, you need to build a system that determines whether two Java functions are similar or not.

We are providing the functions along with their corresponding embeddings, generated using the JINA model, as well as the similarity labels assigned by expert developers.

We would like you to discuss the results and share your suggestions on how to determine whether two pieces of code are similar.

[OPTIONAL] If you have time, you may want to deepen your initial solution, Here are some potential ideas for your inspiration (not restrictive, you can come up with other ideas):

' Compare with a different embedding model (e.g. UnixCoder)

' Closer inspection of the applicability of your solution to use cases in the real life. An example: how can this be applied to repo clone detection, i.e. detecting if there are some similar functions in big repos?

## Overview

This repository contains a dataset for code similarity analysis. The dataset consists of pairs of Java code snippets that have been manually labeled for similarity.

## Dataset Structure

### Data Folder

The `Data/` folder contains Java code pairs organized into 5 batches:

```
Data/
├── result_batch1/
├── result_batch2/
├── result_batch3/
├── result_batch4/
└── result_batch5/
```

Each batch contains numbered subdirectories (e.g., `1/`, `2/`, `3/`, etc.), and each subdirectory contains a pair of Java code files and their corresponding embeddings:

- **LeftRawCode.java** - The left code snippet for comparison
- **RightRawCode.java** - The right code snippet for comparison
- **LeftRawCode.npy** - Embedding vector for the left code snippet
- **RightRawCode.npy** - Embedding vector for the right code snippet

#### Embeddings

The code embeddings are generated using the **`jina-embeddings-v2-base-code`** model, a state-of-the-art transformer model specifically designed for code understanding and similarity detection. Each embedding is stored as a NumPy array (`.npy` file) containing a dense vector representation of the corresponding code snippet.

#### Example Structure

```
Data/result_batch1/
├── 1/
│   ├── LeftRawCode.java
│   ├── LeftRawCode.npy
│   ├── RightRawCode.java
│   └── RightRawCode.npy
├── 2/
│   ├── LeftRawCode.java
│   ├── LeftRawCode.npy
│   ├── RightRawCode.java
│   └── RightRawCode.npy
└── ...
```

### Labels Folder

The `Labels/` folder contains similarity labels for the code pairs in each batch:

```
Labels/
├── label_1.csv
├── label_2.csv
├── label_3.csv
├── label_4.csv
└── label_5.csv
```

Each label file corresponds to a batch in the Data folder:
- `label_1.csv` → labels for `result_batch1/`
- `label_2.csv` → labels for `result_batch2/`
- `label_3.csv` → labels for `result_batch3/`
- `label_4.csv` → labels for `result_batch4/`
- `label_5.csv` → labels for `result_batch5/`

#### Label File Format

Each CSV file contains two columns:

| Column | Description |
|--------|-------------|
| `line_number` | The index/ID of the code pair (corresponds to the subdirectory number) |
| `similar` | The similarity label: **Yes**, **No**, or **Not Sure** |

#### Example Labels

```csv
line_number,similar
1,Yes
2,Yes
3,No
4,No
5,Yes
6,No
7,No
8,Not Sure
...
```

## Similarity Labels

The dataset uses three possible similarity labels:

- **Yes** - The left and right code snippets are similar
- **No** - The left and right code snippets are not similar
- **Not Sure** - The similarity is uncertain or ambiguous

## Source Code
At the beginning of each source code file, you’ll find the source reference. For example:

```java
// https://github.com/ximsfei/Android-skin-support/tree/3eaa81f5999342018186fb28a2d915fc8605e4c4/demo/skin-app/src/main/java/com/ximsfei/skindemo/tab/fragment/CustomDividerItemDecoration.java#L94-L119
```
