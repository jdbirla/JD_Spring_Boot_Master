package com.jd.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SomeBusinessMockAnnotationTest {

	@Mock
	DataService dataServiceMock  ;
	
	@InjectMocks
	SomeBusinessImpl businessImpl;
	
	@Test
	void testfindTheGreatestFromAllData() {
		//DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		
		//SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
	
	@Test
	void testfindTheGreatestFrom_OneVuale() {
		//DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		
		//SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
	
	

}
