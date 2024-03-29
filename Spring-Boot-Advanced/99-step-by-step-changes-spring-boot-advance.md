# Coding and Debugging Guide

## Step01

- Step 01 - Quick Introduction to REST - Understand Resource and Actions

On start.spring.io, Choose
- Latest version of Spring Boot and Java
- groupId: `com.in28minutes.springboot`
- artifactId: `first-rest-api`
- Dependencies: Spring Boot Starter Web and DevTools


## Step05

- Step 02 - Creating Spring Boot Project for REST with Maven and Eclipse
- Step 03 - Creating your first Spring Boot Resource - Hello World
- Step 04 - Creating a Second Spring Boot Resource Method - Hello World Bean
- Step 05 - Exploring Path Params and Path Variables with Spring Boot


### /src/main/java/com/in28minutes/springboot/firstrestapi/helloworld/HelloWorldBean.java New

```java
package com.in28minutes.springboot.firstrestapi.helloworld;

public class HelloWorldBean {

	//Constructor, Getters and ToString

	private String message;

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/helloworld/HelloWorldResource.java New

```java
package com.in28minutes.springboot.firstrestapi.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloWorldResource {
	// /hello-world => "Hello World"
	
	@RequestMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	
	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	//Path Variable or Path Params
	// /user/Ranga/todos/1
	
	@RequestMapping("/hello-world-path-param/{name}")
	public HelloWorldBean helloWorldPathParam(@PathVariable String name) {
		return new HelloWorldBean("Hello World, " + name);
	}
	
	@RequestMapping("/hello-world-path-param/{name}/message/{message}")
	public HelloWorldBean helloWorldMultiplePathParam
					(@PathVariable String name,
							@PathVariable String message) {
		return new HelloWorldBean("Hello World " + name + "," + message);
	}

}
```
---

### /src/main/resources/application.properties Modified
New Lines
```
logging.level.org.springframework=debug
```

## Step06

- Step 06 - Getting Ready for Survey Questionnaire REST API

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/Question.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import java.util.List;

public class Question {

	//Constructor, Getters and ToString
	
	private String id;
	private String description;
	private List<String> options;
	private String correctAnswer;

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/Survey.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import java.util.List;

public class Survey {

	//Constructor, Getters and ToString

	private String id;
	private String title;
	private String description;
	private List<Question> questions;

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {
	
	private static List<Survey> surveys = new ArrayList<>();
	
	static {
	
		Question question1 = new Question("Question1",
		        "Most Popular Cloud Platform Today", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2",
		        "Fastest Growing Cloud Platform", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3",
		        "Most Popular DevOps Tool", Arrays.asList(
		                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
		        question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
		        "Description of the Survey", questions);

		surveys.add(survey);

		
	}

}
```
---

## Step07
- Step 07 - Creating First Survey Spring Boot REST API - GET all surveys


### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyResource {
	
	private SurveyService surveyService;
	
	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	// /surveys => surveys
	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys(){
		return surveyService.retrieveAllSurveys();
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified

```java
public List<Survey> retrieveAllSurveys() {
	return surveys;
}
```
---

## Step08

- Step 08 - Creating Second Survey Spring Boot REST API Method - GET a survey
- Step 09 - Exploring REST API Best Practices - Request Methods and Response Status


### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java Modified

```java
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/surveys/{surveyId}")
public Survey retrieveSurveyById(@PathVariable String surveyId){
	Survey survey = surveyService.retrieveSurveyById(surveyId);
	
	if(survey==null)
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
	return survey;
}

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified

```
import java.util.Optional;
import java.util.function.Predicate;

public Survey retrieveSurveyById(String surveyId) {
	
	Predicate<? super Survey> predicate =
			survey -> survey.getId().equalsIgnoreCase(surveyId);
	
	Optional<Survey> optionalSurvey 
			= surveys.stream().filter(predicate).findFirst();
	
	if(optionalSurvey.isEmpty()) return null;
	
	return optionalSurvey.get();
}
```
---

## Step10
- Step 10 - Exercise - Creating Survey Question related Spring Boot REST API Methods


### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java Modified

```java
	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId){
		List<Question> questions = surveyService.retrieveAllSurveyQuestions(surveyId);
		
		if(questions==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return questions;
	}

	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId){
		Question question = surveyService.retrieveSpecificSurveyQuestion
										(surveyId, questionId);
		
		if(question==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return question;
	}

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified

```java

	public List<Question> retrieveAllSurveyQuestions(String surveyId) {
		Survey survey = retrieveSurveyById(surveyId);

		if (survey == null)
			return null;

		return survey.getQuestions();
	}

	public Question retrieveSpecificSurveyQuestion(String surveyId, String questionId) {

		List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);

		if (surveyQuestions == null)
			return null;

		Optional<Question> optionalQuestion = surveyQuestions.stream()
				.filter(q -> q.getId().equalsIgnoreCase(questionId)).findFirst();

		if (optionalQuestion.isEmpty())
			return null;

		return optionalQuestion.get();
	}

```
---

## Step11

- Step 11 - Creating Spring Boot REST API to create Survey Question - POST

##### POST

**URL**: http://localhost:8080/surveys/Survey1/questions/
**Header**: Content-Type:application/json

**Request Body**
```
{
    "description": "Your Favorite Cloud Platform",
    "options": [
        "AWS",
        "Azure",
        "Google Cloud",
        "Oracle Cloud"
    ],
    "correctAnswer": "Google Cloud"
}

```


### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java Modified
New Lines
```java
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/surveys/{surveyId}/questions", method = RequestMethod.POST)
public void addNewSurveyQuestion(@PathVariable String surveyId,
		@RequestBody Question question){
	
	surveyService.addNewSurveyQuestion(surveyId, question);
	
}

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified
New Lines
```java

	public void addNewSurveyQuestion(String surveyId, Question question) {
		List<Question> questions = retrieveAllSurveyQuestions(surveyId);
		questions.add(question);
	}

```
---

## Step12
- Step 12 - Improving POST Method - Status CREATED and Location Header

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/Question.java Modified
New Lines
```java
	public void setId(String id) {
		this.id = id;
	}
```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java Modified
New Lines
```java
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(value="/surveys/{surveyId}/questions", method = RequestMethod.POST)
public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyId,
		@RequestBody Question question){
	
	String questionId = surveyService.addNewSurveyQuestion(surveyId, question);
	// /surveys/{surveyId}/questions/{questionId}
	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{questionId}").buildAndExpand(questionId).toUri();
	return ResponseEntity.created(location ).build();
	
}

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified
New Lines
```java
import java.math.BigInteger;
import java.security.SecureRandom;

public String addNewSurveyQuestion(String surveyId, Question question) {
	List<Question> questions = retrieveAllSurveyQuestions(surveyId);
	question.setId(generateRandomId());
	questions.add(question);
	return question.getId();
}

private String generateRandomId() {
	SecureRandom secureRandom = new SecureRandom();
	String randomId = new BigInteger(32, secureRandom).toString();
	return randomId;
}

```
---

## Step13
- Step 13 - Implementing Spring Boot REST API Method to DELETE a Question


### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java Modified
New Lines
```java
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId){
		surveyService.deleteSurveyQuestion(surveyId, questionId);
		return ResponseEntity.noContent().build();
	}

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified
New Lines
```java
	public String deleteSurveyQuestion(String surveyId, String questionId) {

		List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);

		if (surveyQuestions == null)
			return null;
		

		Predicate<? super Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);
		boolean removed = surveyQuestions.removeIf(predicate);
		
		if(!removed) return null;

		return questionId;
	}

```
---

## Step14

- Step 14 - Implementing Spring Boot REST Method to Update a Question - PUT

##### PUT

**URL**: http://localhost:8080/surveys/Survey1/questions/Question1
**Header**: Content-Type:application/json
**Request Body**
```
{
    "id": "Question1",
    "description": "Most Popular Cloud Platform Today Change",
    "options": [
        "AWS",
        "Azure",
        "Google Cloud",
        "Oracle Cloud"
    ],
    "correctAnswer": "Google Cloud"
}

```


### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResource.java Modified
New Lines
```java

	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId,
			@RequestBody Question question){
		
		surveyService.updateSurveyQuestion(surveyId, questionId, question);
		
		return ResponseEntity.noContent().build();
	}

```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/survey/SurveyService.java Modified
New Lines
```java

	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
		List<Question> questions = retrieveAllSurveyQuestions(surveyId);
		questions.removeIf(q -> q.getId().equalsIgnoreCase(questionId));
		questions.add(question);
	}

```
---

## Step16

- Step 15 - Setting up Spring Boot Data JPA with H2 Database and User Entity
- Step 16 - Exploring Spring Boot Data JPA using Command Line Runner

### /pom.xml Modified
New Lines
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```
### /src/main/java/com/in28minutes/springboot/firstrestapi/user/UserDetails.java New

```java
package com.in28minutes.springboot.firstrestapi.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserDetails {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String role;
	
	public UserDetails() {
		
	}
	
	public UserDetails(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + ", role=" + role + "]";
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/user/UserDetailsCommandLineRunner.java New

```java
package com.in28minutes.springboot.firstrestapi.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

	public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
		super();
		this.repository = repository;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	private UserDetailsRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new UserDetails("Ranga", "Admin"));
		repository.save(new UserDetails("Ravi", "Admin"));
		repository.save(new UserDetails("John", "User"));
		
		//List<UserDetails> users = repository.findAll();
		
		List<UserDetails> users = repository.findByRole("Admin");
		
		users.forEach(user -> logger.info(user.toString()));
		
		
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/firstrestapi/user/UserDetailsRepository.java New

```java
package com.in28minutes.springboot.firstrestapi.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{
	List<UserDetails> findByRole(String role);
}
```
---

### /src/main/resources/application.properties Modified
New Lines
```
logging.level.org.springframework=info
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.show-sql=true
```

## Step17

- Step 17 - Creating User REST API with Spring Boot Starter Rest

### /src/main/java/com/in28minutes/springboot/firstrestapi/user/UserDetailsRestRepository.java New

```java
package com.in28minutes.springboot.firstrestapi.user;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDetailsRestRepository extends PagingAndSortingRepository<UserDetails, Long>{
	List<UserDetails> findByRole(String role);
}
```


## Step19

- Step 18 - Writing Your First Spring Boot Integration Test
- Step 19 - Writing Asserts for JSON in Spring Boot Tests - JsonAssert


### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/JsonAssertTest.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class JsonAssertTest {

	@Test
	void jsonAssert_learningBasics() throws JSONException {
		
		String expectedResponse =
				"""
				{
					"id":"Question1",
					"description":"Most Popular Cloud Platform Today",
					"correctAnswer":"AWS"
				}
				""";
		
		String actualResponse =
				"""
				  {"id":"Question1",
				  "description":"Most Popular Cloud Platform Today",
				  "options":["AWS","Azure","Google Cloud","Oracle "],
				  "correctAnswer":"AWS"}
				""";

		JSONAssert.assertEquals(expectedResponse, actualResponse, false);

	}

}
```
---

### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResourceIT.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
	
	//http://localhost:8080/surveys/Survey1/questions/Question1
	String str = """
			
			{
			  "id": "Question1",
			  "description": "Most Popular Cloud Platform Today",
			  "options": [
			    "AWS",
			    "Azure",
			    "Google Cloud",
			    "Oracle Cloud"
			  ],
			  "correctAnswer": "AWS"
			}
			
			""";
	
	
	// 
	
	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
	
	@Autowired
	private TestRestTemplate template;
	
	//
	//[Content-Type:"application/json", 

	
	@Test
	void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
		ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);

		String expectedResponse =
				"""
				{
					"id":"Question1",
					"description":"Most Popular Cloud Platform Today",
					"correctAnswer":"AWS"
				}
				""";

		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
		//assertEquals(expectedResponse.trim(), responseEntity.getBody());
		//System.out.println();
		//System.out.println(responseEntity.getHeaders());
	}
	
	
}
```
---
## Step21
- Step 20 - Improving JUnit Asserts for Spring Boot Integration Test
- Step 21 - Writing Spring Boot Integration Test for GET method returning List


### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResourceIT.java Modified

```java
package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
	
	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
	
	private static String GENERIC_QUESTIONS_URL = "/surveys/Survey1/questions/";
	
	@Autowired
	private TestRestTemplate template;

	String str = """
			
			{
			  "id": "Question1",
			  "description": "Most Popular Cloud Platform Today",
			  "options": [
			    "AWS",
			    "Azure",
			    "Google Cloud",
			    "Oracle Cloud"
			  ],
			  "correctAnswer": "AWS"
			}
			
			""";
		
	@Test
	void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
		
		ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);

		String expectedResponse =
				"""
				{
					"id":"Question1",
					"description":"Most Popular Cloud Platform Today",
					"correctAnswer":"AWS"
				}
				""";

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
		
		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
		 
	}
	
	@Test
	void retrieveAllSurveyQuestions_basicScenario() throws JSONException {
		
		ResponseEntity<String> responseEntity = template.getForEntity(GENERIC_QUESTIONS_URL, String.class);

		String expectedResponse =
				"""
						[
						  {
						    "id": "Question1"
						  },
						  {
						    "id": "Question2"
						  },
						  {
						    "id": "Question3"
						  }
						]
				
				""";

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
		
		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
		 
	}

}
```
---
## Step23

- Step 22 - Writing Spring Boot Integration Test for POST method creating a Question
- Step 23 - Understanding JUnit Best Practice - Have ZERO Side Effects


### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResourceIT.java Modified
New Lines
```java
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

	
@Test
void addNewSurveyQuestion_basicScenario() {

	String requestBody = """
				{
				  "description": "Your Favorite Language",
				  "options": [
				    "Java",
				    "Python",
				    "JavaScript",
				    "Haskell"
				  ],
				  "correctAnswer": "Java"
				}
			""";

	
	//
	
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Type", "application/json");
	
	HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
	
	ResponseEntity<String> responseEntity 
		= template.exchange(GENERIC_QUESTIONS_URL, HttpMethod.POST, httpEntity, String.class);
	
	assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	
	String locationHeader = responseEntity.getHeaders().get("Location").get(0);
	assertTrue(locationHeader.contains("/surveys/Survey1/questions/"));
	
	//DELETE
	//locationHeader
	//REMOVE SIDE EFFECT	
	template.delete(locationHeader);
	
}

```
---
## Step26

- Step 24 - Writing Your First Spring Boot Mock MVC Unit Test
- Step 25 - Improving Asserts for Spring Boot Mock MVC Unit Test
- Step 26 - Writing Spring Boot Mock MVC Unit Test for POST Method


### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResourceTest.java New

```java
package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


//SurveyResource
@WebMvcTest(controllers = SurveyResource.class)
class SurveyResourceTest {
	
	@MockBean
	private SurveyService surveyService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static String SPECIFIC_QUESTION_URL = "http://localhost:8080/surveys/Survey1/questions/Question1";
	
	private static String GENERIC_QUESTION_URL = "http://localhost:8080/surveys/Survey1/questions/";
	
	@Test
	void retrieveSpecificSurveyQuestion_404Scenario() throws Exception {
		RequestBuilder requestBuilder = 
				MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(404, mvcResult.getResponse().getStatus());
		
	}

	
	@Test
	void retrieveSpecificSurveyQuestion_basicScenario() throws Exception {
		RequestBuilder requestBuilder = 
				MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);
		
		
		Question question = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");

		when(surveyService.retrieveSpecificSurveyQuestion("Survey1", "Question1")).thenReturn(question);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();		
	
		String expectedResponse = """
				{
				
					"id":"Question1",
					"description":"Most Popular Cloud Platform Today",
					"options":["AWS","Azure","Google Cloud","Oracle Cloud"],
					"correctAnswer":"AWS"
				
				}
						
				"""; 
		
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		assertEquals(200, response.getStatus());
		JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false);
		
		
	}
	
	@Test
	void addNewSurveyQuestion_basicScenario() throws Exception {

		String requestBody = """
				{
				  "description": "Your Favorite Language",
				  "options": [
				    "Java",
				    "Python",
				    "JavaScript",
				    "Haskell"
				  ],
				  "correctAnswer": "Java"
				}
			""";
		
		when(surveyService.addNewSurveyQuestion(anyString(),any())).thenReturn("SOME_ID");

		RequestBuilder requestBuilder = 
				MockMvcRequestBuilders.post(GENERIC_QUESTION_URL)
				.accept(MediaType.APPLICATION_JSON)
				.content(requestBody).contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();		
		
		MockHttpServletResponse response = mvcResult.getResponse();
		String locationHeader = response.getHeader("Location");
		
		assertEquals(201, response.getStatus());
		assertTrue(locationHeader.contains("/surveys/Survey1/questions/SOME_ID"));
		
	}
	
}
```
---
## Step28

- Step 27 - Getting Started with Spring Boot Starter Security
- Step 28 - Configuring Spring Security for Spring Boot REST API

#### Basic Authorization Header

- admin - password
- Authorization - Basic YWRtaW46cGFzc3dvcmQ=


### /pom.xml Modified
New Lines
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

```
### /src/main/java/com/in28minutes/springboot/firstrestapi/security/SpringSecurityConfiguration.java New

```java
package com.in28minutes.springboot.firstrestapi.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	//LDAP or Database
	//In Memory 
	
	//InMemoryUserDetailsManager
	//InMemoryUserDetailsManager(UserDetails... users)
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("admin", "password");
		UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disable
	//Frames
	
	// dothis
	// dothis
	// executeRequest
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		http.httpBasic(withDefaults());
		
		http.csrf().disable(); //POST or PUT
		
		http.headers().frameOptions().disable();
		
		return http.build();
	}
}
```
---

## Step29
- Step 29 - Fixing Spring Boot Unit and Integration Tests


### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResourceIT.java Modified

Few Important New Lines
```
import java.util.Base64;
HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
//New Method
private HttpHeaders createHttpContentTypeAndAuthorizationHeaders() {
```

```java
package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
	
	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
	
	private static String GENERIC_QUESTIONS_URL = "/surveys/Survey1/questions/";
	
	
	@Autowired
	private TestRestTemplate template;
		
	@Test
	void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {

		HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> responseEntity 
			= template.exchange(SPECIFIC_QUESTION_URL, HttpMethod.GET, httpEntity, String.class);
		
		//ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);

		String expectedResponse =
				"""
				{
					"id":"Question1",
					"description":"Most Popular Cloud Platform Today",
					"correctAnswer":"AWS"
				}
				""";

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
		
		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
		 
	}
	
	@Test
	void retrieveAllSurveyQuestions_basicScenario() throws JSONException {
		
		HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> responseEntity 
			= template.exchange(GENERIC_QUESTIONS_URL, HttpMethod.GET, httpEntity, String.class);

		
		//ResponseEntity<String> responseEntity = template.getForEntity(GENERIC_QUESTIONS_URL, String.class);

		String expectedResponse =
				"""
						[
						  {
						    "id": "Question1"
						  },
						  {
						    "id": "Question2"
						  },
						  {
						    "id": "Question3"
						  }
						]
				
				""";

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
		
		JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
		 
	}

	
	@Test
	void addNewSurveyQuestion_basicScenario() {

		String requestBody = """
					{
					  "description": "Your Favorite Language",
					  "options": [
					    "Java",
					    "Python",
					    "JavaScript",
					    "Haskell"
					  ],
					  "correctAnswer": "Java"
					}
				""";

		
		HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> responseEntity 
			= template.exchange(GENERIC_QUESTIONS_URL, HttpMethod.POST, httpEntity, String.class);
		
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		
		String locationHeader = responseEntity.getHeaders().get("Location").get(0);
		assertTrue(locationHeader.contains("/surveys/Survey1/questions/"));
		
		//DELETE
		//locationHeader

		ResponseEntity<String> responseEntityDelete 
		= template.exchange(locationHeader, HttpMethod.DELETE, httpEntity, String.class);

		assertTrue(responseEntityDelete.getStatusCode().is2xxSuccessful());
		//template.delete(locationHeader);
		
	}

	private HttpHeaders createHttpContentTypeAndAuthorizationHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic " + performBasicAuthEncoding("admin","password"));
		return headers;
	}
	
	
	String performBasicAuthEncoding(String user, String password) {
		String combined = user + ":" + password;
		byte[] encodedBytes = Base64.getEncoder().encode(combined.getBytes());
		return new String(encodedBytes);
	}
	
}
```
---

### /src/test/java/com/in28minutes/springboot/firstrestapi/survey/SurveyResourceTest.java Modified
New Lines
```java
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
@AutoConfigureMockMvc(addFilters = false)
```
---