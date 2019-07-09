package com.hcl.ecommerce.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	
	public String encodePassword(String password)
	{
		return Base64.getEncoder().encodeToString(password.getBytes());
	}
	
	
}