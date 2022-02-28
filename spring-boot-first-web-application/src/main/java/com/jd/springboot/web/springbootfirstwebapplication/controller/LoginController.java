package com.jd.springboot.web.springbootfirstwebapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	public String sayHello(@RequestParam String name, ModelMap map )
	{
		map.put("parama1", name);
		return "login" ;
	}

}
