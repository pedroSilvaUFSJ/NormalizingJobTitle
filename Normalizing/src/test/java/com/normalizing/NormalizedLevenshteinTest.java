package com.normalizing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NormalizedLevenshteinTest {

	@Test
	final void checkEquals() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(1.0, instance.similarity("primeira palavra a ser testada", "primeira palavra a ser testada"),
				0.0000001);
	}

	@Test
	final void checkSensiteCase() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(1.0, instance.similarity("primeira palavra a ser testada", "Primeira PALAVRA a ser testada"),
				0.0000001);
	}

	@Test
	final void checkDiff() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(0, instance.similarity("aaaaa", "bbbbb"), 0.0000001);
	}

	@Test
	final void checkHalfDiff() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(0.5, instance.similarity("aaaa", "aabb"), 0.0000001);
	}

	@Test
	final void checkQuarter() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(0.75, instance.similarity("aaaa", "abaa"), 0.0000001);
	}

	@Test
	final void checkFirstInputEmpty() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(0, instance.similarity("", "aaaa"), 0.0000001);
	}

	@Test
	final void checkSecondEmpty() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(0, instance.similarity("aaaa", ""), 0.0000001);
	}

	@Test
	final void checkBothEmpty() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		assertEquals(1, instance.similarity("", ""), 0.0000001);
	}

	@Test
	final void itShouldThrowNullPointerExceptionWhenFirstInputIsNull() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			instance.similarity(null, "aaaa");
		});
		assertEquals("First input must not be null", exception.getMessage());
	}

	@Test
	final void itShouldThrowNullPointerExceptionWhenSecondInputIsNull() {
		NormalizedLevenshtein instance = new NormalizedLevenshtein();
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			instance.similarity("aaaa", null);
		});
		assertEquals("Second input must not be null", exception.getMessage());
	}
}
