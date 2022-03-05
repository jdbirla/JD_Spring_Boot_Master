package com.jd.springboot.web.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	public boolean isValidUser(String userName,String password )
	{
		boolean flag = false;

		if (userName.equalsIgnoreCase("JD") && password.equalsIgnoreCase("Jitu"))
			flag = true;

		return flag;
	}

}
