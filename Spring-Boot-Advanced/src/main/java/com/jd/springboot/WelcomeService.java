package com.jd.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jd.springboot.configuration.BasicConfiguration;

@Component
public class WelcomeService {

	@Value("${welcome.message}")
	private String welcomeMessage;
	

	public String retrieveWelcomeMessage() {
		// Complex Method
		// return "Good Morning updated";
		return welcomeMessage;
	}

	
}