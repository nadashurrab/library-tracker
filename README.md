# Library Tracker

A tiny Java command-line program that adds, lists, searches and removes books stored in `books.json`.
Built for **Assignment 2 — Team Git: GitHub Flow, Reviews & Recovery** (Software Engineering).

## Team

| Role      | Name              | GitHub              |
|-----------|-------------------|---------------------|
| Student A | Nada Salama Mohammed Shurrab | [@nadashurrab](https://github.com/nadashurrab) |
| Student B | Nada Shurrab | [@nada0sh](https://github.com/nada0sh) |

## Requirements

- Java 17 or newer (no external libraries, no build tool)

## Build & run

```bash
javac -d out src/*.java
java -cp out App add "Clean Code" "Robert C. Martin"
java -cp out App list
java -cp out App search martin
java -cp out App remove 1
```

Books are saved in `books.json` in the folder you run the program from.

## Commands

| Command                         | What it does                         |
|---------------------------------|--------------------------------------|
| `add "Title" "Author"`          | Adds a new book                      |
| `list`                          | Lists all books                      |
| `search keyword`                | Finds books by title or author (case-insensitive) |
| `remove id`                     | Removes the book with that id        |

## Workflow

We use **GitHub Flow**: every change starts from an issue, lives on a short-lived
`feature/*` branch, goes through a reviewed pull request, and is merged into `main`.
Commit messages follow [Conventional Commits](https://www.conventionalcommits.org/).

## Contributing

1. Open an issue describing the change.
2. Create a branch from `main` (`feature/...`, `fix/...` or `docs/...`).
3. Commit with Conventional Commits, e.g. `feat(books): add search`.
4. Open a pull request with `Closes #<issue>` and ask your teammate for a review.
5. Never push or force-push directly to `main`.
