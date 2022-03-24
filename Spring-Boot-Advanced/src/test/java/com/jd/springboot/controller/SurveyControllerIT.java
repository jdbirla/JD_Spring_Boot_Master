package com.jd.springboot.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jd.springboot.Application;

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
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveSurveyQuestion() throws Exception {

        String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
        				 //{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}
        ResponseEntity<String> response = template.exchange(
                createUrl("/surveys/Survey1/questions/Question1"),
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), String.class);

          System.out.println(" response.getBody() -> "+ response.getBody());
          System.out.println(" expected -> "+ expected);
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }


}
