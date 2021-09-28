package com.normalizing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NormaliserTest {
	@Test
	final void itShouldReturnSoftwareEngineerIfInputIsJavaEngineer() {
		Normaliser instance = new Normaliser();
		assertEquals("Software engineer", instance.normalise("Java engineer"));
	}
	
	@Test
	final void itShouldReturnAccountantIfAccountant() {
		Normaliser instance = new Normaliser();
		assertEquals("Accountant", instance.normalise("Accountant"));
	}
}
