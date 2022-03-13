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
## What You Will Learn during this Step 03:
- First installment of revealing how magic happens with Spring Boot. As a Spring Boot developer, you need to understand what's happening beneath the hood of Spring Boot! 
- spring-boot-starter-web : starter for building applications with Spring MVC. Tomcat is default embedded container.
- We already added this starter in the first step! Now we will explore the features it provides
- We will enable logging in DEBUG mode to understand further

### spring-boot-starter-web
- Spring Boot Starter Web brings all dependencies needed to build normal and RESTful web applications. Look at the dependency tree. 
- All the dependencies are added in because of spring-boot-starter-web
- Also look at /META-INF/spring.provides inside the spring-boot-starter-web.jar
- Spring Boot Starter Web auto configures things needed to startup a web application. Look at the log
- Mapping servlet: 'dispatcherServlet' to [/]
- Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
- Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
- Look at package org.springframework.boot.autoconfigure.web in spring-boot-autoconfigure-*.jar
- Go to url http://localhost:8080/some-non-existing-url

### Useful Snippets
/src/main/resources/application.properties
```
logging.level.org.springframework: DEBUG
```
---
## What You Will Learn during this Step 04:
- Understand Starter Parent
- How to override things defined in Starter Parent
- Other starter projects

### Starter Parent
- Dependency Versions
- Java Versions
- Default Plugins

### Other Starter Projects
- spring-boot-starter-web-services
- spring-boot-starter-test
- spring-boot-starter-jdbc
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-data-rest
- More at https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#using-boot-starter

### Useful Snippets and References
First Snippet
```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>
    </parent>
```
---
## What You Will Learn during this Step 05:
- Spring Boot vs Spring
- What Spring Boot is Not!

### Spring Boot vs Spring

### Spring
- Spring is just a dependency injection framework. Spring focuses on the "plumbing" of enterprise applications so that teams can focus on application-level business logic, without unnecessary ties to specific deployment environments.
- First half of the 2000 decade! EJBs
- EJBs were NOT easy to develop. 
- Write a lot of xml and plumbing code to get EJBs running
- Impossible to Unit Test
- Alternative - Writing simple JDBC Code involved a lot of plumbing
- Spring framework started with aim of making Java EE development simpler. 
- Goals
- Make applications testable. i.e. easier to write unit tests
- Reduce plumbing code of JDBC and JMS
- Simple architecture. Minus EJB.
- Integrates well with other popular frameworks.

### Applications with Spring Framework
- Over the next few years, a number of applications were developed with Spring Framework
- Testable but
- Lot of configuration (XML and Java)
- Developing Spring Based application need configuration of a lot of beans!
- Integration with other frameworks need configuration as well!
- In the last few years, focus is moving from monolith applications to microservices. We need to be able to start project quickly. Minimum or Zero start up time
- Framework Setup
- Deployment - Configurability
- Logging, Transaction Management
- Monitoring
- Web Server Configuration

### Spring Boot
- Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can “just run”. 
- We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. 
- Example Problem Statements
- You want to add Hibernate to your project. You dont worry about configuring a data source and a session factory. I will do if for you! 
- Goals
- Provide quick start for projects with Spring.
- Be opinionated but provide options.
- Provide a range of non-functional features that are common to large classes of projects (e.g. embedded servers, security, metrics, health checks, externalized configuration).

#### What Spring Boot is NOT?
- It’s not an app or a web server
- Does not implement any specific framework - for example, JPA or JMS
- Does not generate code

---
