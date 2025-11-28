#!/usr/bin/env bash
set -euo pipefail

# Usage:
# ./scripts/publish_docs_https.sh [REPO_URL] [CLONE_DIR] [SRC_DOCS] [BRANCH]
# Example:
# ./scripts/publish_docs_https.sh https://github.com/skc147283/sdet-plan.git /tmp/sdet-plan-publish /Users/sureshchoppara/IdeaProjects/interviewPrep/docs main

REPO_URL="${1:-https://github.com/skc147283/sdet-plan.git}"
CLONE_DIR="${2:-/tmp/sdet-plan-publish}"
SRC_DOCS="${3:-/Users/sureshchoppara/IdeaProjects/interviewPrep/docs}"
BRANCH_PARAM="${4:-main}"

echo "Repo URL: $REPO_URL"
echo "Clone dir: $CLONE_DIR"
echo "Source docs: $SRC_DOCS"

if [ ! -d "$SRC_DOCS" ]; then
  echo "ERROR: Source docs directory does not exist: $SRC_DOCS" >&2
  exit 1
fi

# Clean clone dir
rm -rf "$CLONE_DIR"

echo "Cloning repository..."
git clone "$REPO_URL" "$CLONE_DIR"
cd "$CLONE_DIR"

# Detect default branch from remote if possible
DETECTED_BRANCH=$(git remote show origin | sed -n 's/.*HEAD branch: //p' || true)
if [ -n "$DETECTED_BRANCH" ]; then
  BRANCH="$DETECTED_BRANCH"
else
  BRANCH="$BRANCH_PARAM"
fi

echo "Using branch: $BRANCH"

# Ensure branch exists locally
git fetch origin "$BRANCH" || true
if git show-ref --verify --quiet refs/heads/$BRANCH; then
  git checkout $BRANCH
else
  # Try to checkout remote branch, otherwise create it locally tracking origin
  if git ls-remote --exit-code --heads origin $BRANCH >/dev/null 2>&1; then
    git checkout -b $BRANCH origin/$BRANCH
  else
    git checkout -b $BRANCH
  fi
fi

# Replace docs folder
rm -rf docs
cp -R "$SRC_DOCS" ./docs
# Ensure .nojekyll present
if [ ! -f docs/.nojekyll ]; then
  touch docs/.nojekyll
fi

# Stage changes
git add docs

# If there are no staged changes, exit gracefully
if git diff --cached --quiet; then
  echo "No changes to docs; nothing to commit."
  echo "If you expect changes, confirm the source path and try again."
  exit 0
fi

# Commit & push
COMMIT_MSG="chore(pages): add/update docs site"
git commit -m "$COMMIT_MSG"

echo "Pushing to origin/$BRANCH..."
if git push origin $BRANCH; then
  echo "Push succeeded."
  echo "Visit: https://skc147283.github.io/sdet-plan/ (after Pages is enabled and a minute passes)"
else
  echo "Push failed — likely an authentication issue."
  echo "Try running the script locally and ensure your git HTTPS credentials (username + PAT) are configured or use SSH." >&2
  exit 2
fi
