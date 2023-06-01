# DictionaryDiff

We are currently comparing 10 different european dictionaries with the Levenstein distance.

You have this options:


1. Compare one to the rest
2. Compare one to one
3. Use optimized version


## Levenshtein Formula

The Levenshtein formula, also known as the Levenshtein distance or edit distance, is a measure of the difference between two strings. It calculates the minimum number of single-character edits (insertions, deletions, or substitutions) required to transform one string into another.

### Formula

The Levenshtein distance between two strings, often denoted as `lev(s, t)`, is calculated using the following formula:

lev(s, t) = max(|s|, |t|) - LCS(s, t)

where:
- `|s|` is the length of string `s`
- `|t|` is the length of string `t`
- `LCS(s, t)` is the length of the Longest Common Subsequence between strings `s` and `t`

### Optimized version

  In order to avoid O(n²) time complexity, we must compare only words that have similar length. We've developed a python script called ``
