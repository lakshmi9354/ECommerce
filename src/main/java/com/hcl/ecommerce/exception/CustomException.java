package com.hcl.ecommerce.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -660593431929261693L;

	public CustomException(String message) {
		super(message);
	}
}