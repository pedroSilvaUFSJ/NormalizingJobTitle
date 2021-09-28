package com.normalizing;

import com.interfaces.Normalizer;

/**
 * The Levenshtein distance between two words is the minimum number of
 * single-character edits (insertions, deletions or substitutions) required to
 * change one string into the other.
 *
 * @author Thibault Debatty
 */
public class NormalizedLevenshtein implements Normalizer {
	private static final long serialVersionUID = 1318648129946920632L;

	/**
	 * Equivalent to distance(s1, s2, Integer.MAX_VALUE).
	 */
	public final double distance(final String firstInput, final String secondInput) throws NullPointerException {
		if (firstInput.equals(secondInput)) {
			return 0;
		}
		int maxLength = Math.max(firstInput.length(), secondInput.length());
		if (maxLength == 0) {
			return 0;
		} else {
			return distance(firstInput, secondInput, Integer.MAX_VALUE) / maxLength;
		}
	}

	/**
	 * The Levenshtein distance, or edit distance, between two words is the minimum
	 * number of single-character edits (insertions, deletions or substitutions)
	 * required to change one word into the other.
	 *
	 * http://en.wikipedia.org/wiki/Levenshtein_distance
	 *
	 * It is always at least the difference of the sizes of the two strings. It is
	 * at most the length of the longer string. It is zero if and only if the
	 * strings are equal. If the strings are the same size, the Hamming distance is
	 * an upper bound on the Levenshtein distance. The Levenshtein distance verifies
	 * the triangle inequality (the distance between two strings is no greater than
	 * the sum Levenshtein distances from a third string).
	 *
	 * Implementation uses dynamic programming (Wagner–Fischer algorithm), with only
	 * 2 rows of data. The space requirement is thus O(m) and the algorithm runs in
	 * O(mn).
	 *
	 * @param firstInput  The first string to compare.
	 * @param secondInput The second string to compare.
	 * @param limit       The maximum result to compute before stopping. This means
	 *                    that the calculation can terminate early if you only care
	 *                    about strings with a certain similarity. Set this to
	 *                    Integer.MAX_VALUE if you want to run the calculation to
	 *                    completion in every case.
	 * @return The computed Levenshtein distance.
	 * @throws NullPointerException if s1 or s2 is null.
	 */
	public final double distance(final String firstInput, final String secondInput, final int limit)
			throws NullPointerException {
		if (firstInput.equals(secondInput)) {
			return 0;
		}
		if (firstInput.length() == 0) {
			return secondInput.length();
		}
		if (secondInput.length() == 0) {
			return firstInput.length();
		}

		// create two work vectors of integer distances
		int[] vector1 = new int[secondInput.length() + 1];
		int[] vector2 = new int[secondInput.length() + 1];
		int[] vtemp;

		// initialize v0 (the previous row of distances)
		// this row is A[0][i]: edit distance for an empty s
		// the distance is just the number of characters to delete from t
		for (int i = 0; i < vector1.length; i++) {
			vector1[i] = i;
		}

		for (int i = 0; i < firstInput.length(); i++) {
			// calculate v1 (current row distances) from the previous row v0
			// first element of v1 is A[i+1][0]
			// edit distance is delete (i+1) chars from s to match empty t
			vector2[0] = i + 1;

			int minv1 = vector2[0];

			// use formula to fill in the rest of the row
			for (int j = 0; j < secondInput.length(); j++) {
				int cost = 1;
				if (firstInput.charAt(i) == secondInput.charAt(j)) {
					cost = 0;
				}
				vector2[j + 1] = Math.min(vector2[j] + 1, // Cost of insertion
						Math.min(vector1[j + 1] + 1, // Cost of remove
								vector1[j] + cost)); // Cost of substitution
				minv1 = Math.min(minv1, vector2[j + 1]);
			}

			if (minv1 >= limit) {
				return limit;
			}

			// copy v1 (current row) to v0 (previous row) for next iteration
			// Flip references to current and previous row
			vtemp = vector1;
			vector1 = vector2;
			vector2 = vtemp;
		}
		return vector1[secondInput.length()];
	}

	private void checkNullInputs(String firstInput, String secondInput) throws NullPointerException {
		if (firstInput == null) {
			throw new NullPointerException("First input must not be null");
		}
		if (secondInput == null) {
			throw new NullPointerException("Second input must not be null");
		}
	}

	public final double similarity(final String firstInput, final String secondInput) {
		checkNullInputs(firstInput, secondInput);
		return 1.0 - distance(firstInput.toLowerCase(), secondInput.toLowerCase());
	}
}
