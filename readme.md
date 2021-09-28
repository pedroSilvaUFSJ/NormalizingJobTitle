# Normalizing titles job
Provided with a list of ideal (normalized) job titles, the implementation returns the best match when provided with an
input string.
In order to consider a quality score q, where 0.0 <= q <= 1.0, to find the closest match. The implementation uses the  **Normalized Levenshtein Distance Metric**