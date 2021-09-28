package com.normalizing;

import java.util.Arrays;
import java.util.List;

public class Normaliser extends NormalizedLevenshtein {
	private static final long serialVersionUID = 1L;

	private List<String> normalizedJobTitles;

	public Normaliser() {
		this.normalizedJobTitles = Arrays.asList("Architect", "Software engineer", "Quantity surveyor", "Accountant");
	}

	public String normalise(String input) {
		NormalizedLevenshtein normalized = new NormalizedLevenshtein();
		Double score = 0d;
		String response = "";
		for (String job : this.normalizedJobTitles) {
			double currentScore = normalized.similarity(job, input);
			if (currentScore == 1) {
				return job;
			} else if (score < currentScore) {
				score = currentScore;
				response = job;
			}
		}
		return response;
	}
}
