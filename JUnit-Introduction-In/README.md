# Introduction of JUnit

## Step 1 : What is JUnit and Unit Testing?
 - What is JUnit?
 - What is Unit Testing?
 - Advantages of Unit Testing

## Step 2 : First JUnit Project and Green Bar
 - What is JUnit?
 - First Project with JUnit
 - First JUnit Class
 - No Failure is Success
 - MyMath class with sum method

## Step 3 : First Code and First Unit Test
 - Unit test for the sum method

## Step 4 : Other assert methods
 - assertTrue and assertFalse methods

## Step 5 : Important annotations
  - @Before @After annotations
  - @BeforeClass @AfterClass annotations

## Complete Code Example

### /src/com/in28minutes/junit/MyMath.java

* com.jd.junit.MyMath

```java
package com.jd.junit;

public class MyMath {
	int sum(int[] numbers) {
		int sum = 0;
		for (int i : numbers) {
			sum += i;
		}
		return sum;
	}
}

```


### /test/com/in28minutes/junit/AssertTest.java

* com.jd.junit.AssertTest

```java
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

```

### /test/com/in28minutes/junit/MyMathTest.java

* com.jd.junit.MyMathTest


```java
package com.jd.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class MyMathTest {

	MyMath myMath = new MyMath();

	@Before
	public void before() {
		System.out.println("Before");
	}

	@After
	public void after() {
		System.out.println("After");
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After Class");
	}

	// MyMath.sum
	// 1,2,3 => 6
	@Test
	public void sum_with3numbers() {
		System.out.println("Test1");
		assertEquals(6, myMath.sum(new int[] { 1, 2, 3 }));
	}

	@Test
	public void sum_with1number() {
		System.out.println("Test2");
		assertEquals(3, myMath.sum(new int[] { 3 }));
	}
}

```
---