#!/usr/bin/env bash
set -euo pipefail
# publish_file_https.sh
# Usage: ./scripts/publish_file_https.sh <LOCAL_SRC_FILE> [REPO_URL] [BRANCH] [CLONE_DIR]
# Example:
# ./scripts/publish_file_https.sh /Users/sureshchoppara/IdeaProjects/interviewPrep/src/main/java/studyPlan/MockStudyPlan.html https://github.com/skc147283/sdet-plan.git main /tmp/sdet-publish

LOCAL_SRC_FILE="${1:-}"
REPO_URL="${2:-https://github.com/skc147283/sdet-plan.git}"
BRANCH="${3:-main}"
CLONE_DIR="${4:-/tmp/sdet-publish}"

if [ -z "$LOCAL_SRC_FILE" ]; then
  echo "Usage: $0 <LOCAL_SRC_FILE> [REPO_URL] [BRANCH] [CLONE_DIR]"
  exit 1
fi

if [ ! -f "$LOCAL_SRC_FILE" ]; then
  echo "ERROR: source file not found: $LOCAL_SRC_FILE" >&2
  exit 2
fi

# Clean and clone
rm -rf "$CLONE_DIR"
mkdir -p "$CLONE_DIR"

echo "Cloning $REPO_URL into $CLONE_DIR"
git clone "$REPO_URL" "$CLONE_DIR"
cd "$CLONE_DIR"

# Use provided branch if exists on remote, otherwise create
if git ls-remote --exit-code --heads origin "$BRANCH" >/dev/null 2>&1; then
  git checkout "$BRANCH"
else
  git checkout -b "$BRANCH"
fi

# Ensure docs directory
rm -rf docs
mkdir -p docs

# Copy the file as docs/index.html
cp -f "$LOCAL_SRC_FILE" docs/index.html
# Ensure nojekyll
touch docs/.nojekyll

# Stage and commit
git add docs
if git diff --cached --quiet; then
  echo "No changes to commit."
else
  git commit -m "chore(pages): publish $LOCAL_SRC_FILE as docs/index.html"
fi

# Push
echo "Pushing to origin/$BRANCH..."
git push origin "$BRANCH"

echo "Done. If push succeeded, visit GitHub Pages settings and set Branch: $BRANCH and Folder: /docs"

