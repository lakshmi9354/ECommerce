package com.hcl.ecommerce.exception;

public class EmailValidationException extends RuntimeException {

	private static final long serialVersionUID = -8084638280729828007L;
	
	public EmailValidationException(String message) {
		super(message);
	}

}
