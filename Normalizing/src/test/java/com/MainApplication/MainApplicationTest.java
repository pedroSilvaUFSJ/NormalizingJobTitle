package com.MainApplication;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.normalizing.Normaliser;

public class MainApplicationTest {
	@ParameterizedTest(name = "{0} = {1}")
	@CsvSource({"Java engineer, Software engineer", "C# engineer, Software engineer", "Chief Accountant, Accountant" })
	void sampleGiven(String input, String expectedResult) {
		Normaliser n = new Normaliser();
		String result = n.normalise(input);
		assertEquals(expectedResult, result, () -> input + " should equal " + expectedResult);
	}
}
