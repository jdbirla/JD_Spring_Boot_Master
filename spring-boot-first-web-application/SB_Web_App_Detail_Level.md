# Spring Boot Web Application Detail Level

## What You Will Learn during this Step 01:
- Lets create a simple web application using Spring Boot
- Lets run the Spring Boot Application
- There is a lot of magic happening in here! We will take a deep dive into the magic in Step 03. 

## Files List

### /spring-boot-first-web-application/pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.10</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.jd.springboot.web</groupId>
	<artifactId>spring-boot-first-web-application</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-first-web-application</name>
	<description>Spring boot first web application</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
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
---
### /spring-boot-first-web-application/src/main/java/com/jd/springboot/web/springbootfirstwebapplication/SpringBootFirstWebApplication.java

```java
package com.jd.springboot.web.springbootfirstwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}

}

```
---
### /spring-boot-first-web-application/src/main/resources/application.properties

```
```
---
### /spring-boot-first-web-application/src/test/java/com/jd/springboot/web/springbootfirstwebapplication/SpringBootFirstWebApplicationTests.java

```java
package com.jd.springboot.web.springbootfirstwebapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootFirstWebApplicationTests {

	@Test
	void contextLoads() {
	}

}

```
---
### todo.txt

```
Spring Boot Starter Parent
Spring Boot Starter Web
@SpringBootApplication
Auto Configuration
```
---
## What You Will Learn during this Step 02:
- @RequestMapping(value = "/login", method = RequestMethod.GET)
- http://localhost:8080/login
- Why @ResponseBody?
- Important of RequestMapping method
- How do web applications work? Request and Response
 - Browser sends Http Request to Web Server
 - Code in Web Server => Input:HttpRequest, Output: HttpResponse
 - Web Server responds with Http Response


```
### src/main/resources/application.properties

```
logging.level.org.springframework.web: DEBUG
```


## Exercises
- Create another method with a different mapping returning some other text!

## Files List

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.10</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.jd.springboot.web</groupId>
	<artifactId>spring-boot-first-web-application</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-first-web-application</name>
	<description>Spring boot first web application</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
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
---
### /spring-boot-first-web-application/src/main/java/com/jd/springboot/web/springbootfirstwebapplication/controller/LoginController.java

```java
package com.jd.springboot.web.springbootfirstwebapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public String sayHello()
	{
		return "Hello JD";
	}

}

```
---
### /spring-boot-first-web-application/src/main/java/com/jd/springboot/web/springbootfirstwebapplication/SpringBootFirstWebApplication.java

```java
package com.jd.springboot.web.springbootfirstwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}

}

```
---
### src/main/resources/application.properties

```
logging.level.org.springframework.web: DEBUG

```
---

## What You Will Learn during this Step 03:
- Demystifying some of the magic
 - Spring Boot Starter Parent
 - Spring Boot Starter Web
 - Embedded Tomcat
 - Dev Tools

<br>

---
 
 ## What You Will Learn during this Step 04:
- Your First JSP
- There is a bit of setup before we get there!
- Introduction to View Resolver

## Exercises
- Create a new jsp and a new controller method to redirect to it!
- Play around!


## Useful Snippets and References
First Snippet - /src/main/webapp/WEB-INF/jsp/login.jsp
```html
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
My First JSP!!!
</body>
</html>
```

Second Snippet - /src/main/resources/application.properties
```properties


spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp
logging.level.: DEBUG
```

Third Snippet : To enable jsp support in embedded tomcat server!
```xml
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>

```

Fourth Snippet : To enable jsp support in embedded tomcat server!
```java

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	public String sayHello()
	{
		return "login";
	}

}

```
---