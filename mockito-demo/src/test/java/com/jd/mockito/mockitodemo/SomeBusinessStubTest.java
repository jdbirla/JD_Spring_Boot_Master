package com.jd.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessStubTest {

	@Test
	public void testFindTheGreatestFromAllData() {
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(new DataServiceStub());
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);

	}

}
class DataServiceStub implements DataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 24, 6, 15 };
	}
}