package com.jd.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AssertTest {

	@Test
	public void test() {
		boolean condn = true;
		assertEquals(true, condn);
		assertTrue(condn);
		// assertFalse(condn);
	}

}
