package com.hcl.ecommerce.exception;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 829577266612603423L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
