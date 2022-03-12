## Introduction of Mockito

Mockito is the most famous mocking framework in Java.

- More information 
   - Visit Mockito Official Documentation - [Mockito Documentation] (http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html)

- Step 1 : Setting up an example using http://start.spring.io.
- Step 2 : Using a Stubs - Disadvantages
- Step 3 : Your first mock. 
- Step 4 : Using Mockito Annotations - @Mock, @InjectMocks,
- Step 5 : Mocking List interface
- Next Steps

## Complete Code Example

* pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.jd.mockito</groupId>
	<artifactId>mockito-demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mockito-demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

* com.jd.mockito.mockitodemo.DataService

```java
package com.jd.mockito.mockitodemo;

public interface DataService {

	int[] retrieveAllData();
}

```

* com.jd.mockito.mockitodemo.SomeBusinessImpl

```java
package com.jd.mockito.mockitodemo;

public class SomeBusinessImpl {
	private DataService dataService;

	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	int findTheGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();
		int greatest = Integer.MIN_VALUE;

		for (int value : data) {
			if (value > greatest) {
				greatest = value;
			}
		}
		return greatest;
	}
}

```

### under test folder

* com.jd.mockito.mockitodemo.ListTest

```java
package com.jd.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	public void testSize() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(10);
		assertEquals(10, listMock.size());
		assertEquals(10, listMock.size());
	}

	@Test
	public void testSize_multipleReturns() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(10).thenReturn(20);
		assertEquals(10, listMock.size());
		assertEquals(20, listMock.size());
		assertEquals(20, listMock.size());
	}

	@Test
	public void testGet_SpecificParameter() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	public void testGet_GenericParameter() {
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertEquals("SomeString", listMock.get(1));
	}

}

```


* com.jd.mockito.mockitodemo.SomeBusinessMockAnnotationTest

```java
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

```


* com.jd.mockito.mockitodemo.SomeBusinessMockTest

```java
package com.jd.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class SomeBusinessMockTest {

	@Test
	void testfindTheGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
		// dataServiceMock.retrieveAllData() => new int[] {24,15,3};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
	
	
	@Test
	void testfindTheGreatestFromAllData_15() {
		DataService dataServiceMock = mock(DataService.class);
		// dataServiceMock.retrieveAllData() => new int[] {24,15,3};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {15 });
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}

}

```


* com.jd.mockito.mockitodemo.SomeBusinessStubTest

```java
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
```