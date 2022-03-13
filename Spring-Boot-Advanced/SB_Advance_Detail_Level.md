# Spring Boot Advanced Detail Level Read me

## What You Will Learn during this Step 01:
- Set up an Maven Project with Eclipse. 
- Copy Two Files pom.xml and Application.java
- Launch Your First Spring Boot Application.
- You will be introduced to Maven
- Dependency Management

### code snipet

* src/main/java/com.jd.springboot.Application

```java
package com.jd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

}
```
* pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.jd.springboot</groupId>
  <artifactId>first-springboot-project</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.1.RELEASE</version>
	</parent>
	
	<properties>
		<java.version>1.8</java.version>
	</properties>
	
  
  <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
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
---
## What You Will Learn during this Step 02
- Lets add a RestController with a dependency and see Spring Boot Magic live

## Theory Break : Quick Spring and Spring MVC Primer
- What is dependency?
- @Component
- @Autowired
- @RestController

## Useful Snippets and References

* com.jd.springboot.Application
```java
package com.jd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jd")
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

}
```

* com.jd.springboot.WelcomeController
```java
package com.jd.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jd.service.WelcomeService;

@RestController
public class WelcomeController {

	//Auto wiring
	@Autowired
	private WelcomeService service;

	@RequestMapping("/welcome")
	public String welcome() {
		return service.retrieveWelcomeMessage();
	}
}
```
* com.jd.service.WelcomeService
```java
package com.jd.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

	public String retrieveWelcomeMessage() {
		//Complex Method
		return "Good Morning updated";
	}
}
```
---

