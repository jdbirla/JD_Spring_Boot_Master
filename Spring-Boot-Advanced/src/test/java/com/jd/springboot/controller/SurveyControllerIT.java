package com.jd.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.jd.springboot.Application;
import com.jd.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	private TestRestTemplate template = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setupJSONAcceptType() {
    	System.out.println("Before-JD");
    	headers.add("Authorization", createHttpAuthenticationHeaderValue(
				"user1", "secret1"));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

    private String createHttpAuthenticationHeaderValue(String userId,
			String password) {

		String auth = userId + ":" + password;

		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset
				.forName("US-ASCII")));

		String headerValue = "Basic " + new String(encodedAuth);

		return headerValue;
	}
    
    @Test
    public void testSurveyQuestion() throws Exception {

        String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
        				 //{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}
        String retrieveSpecificQueURL = "/surveys/Survey1/questions/Question1";
		ResponseEntity<String> response = template.exchange(
                createUrl(retrieveSpecificQueURL),
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), String.class);

          System.out.println(" response.getBody() -> "+ response.getBody());
          System.out.println(" expected -> "+ expected);
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void retrieveSurveyQuestions() throws Exception {
        String retreiveAllUqe = "/surveys/Survey1/questions/";
		ResponseEntity<List<Question>> response = template.exchange(
                createUrl(retreiveAllUqe), HttpMethod.GET,
                new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
                new ParameterizedTypeReference<List<Question>>() {
                });

        Question sampleQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                        "India", "Russia", "United States", "China"));

        System.out.println(" response.getBody() -> "+ response.getBody());

        assertTrue(response.getBody().contains(sampleQuestion));
    }
  
  //NEEDS REFACTORING
  	@Test
  	public void addQuestion() {

  		String url = "http://localhost:" + port + "/surveys/Survey1/questions";

  		TestRestTemplate restTemplate = new TestRestTemplate();

  		HttpHeaders headers = new HttpHeaders();

  		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

  		Question question = new Question("DOESNTMATTER", "QuestionJD", "Russia",
  				Arrays.asList("India", "Russia", "United States", "China"));

  		HttpEntity entity = new HttpEntity<Question>(question, headers);

  		ResponseEntity<String> response = restTemplate.exchange(url,
  				HttpMethod.POST, entity, String.class);

  		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        System.out.println(" actual -> "+ actual);

  		assertTrue(actual.contains("/surveys/Survey1/questions/"));

  	}
    
}
