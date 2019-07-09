package com.hcl.ecommerce.exception;

public class UserNameNotSameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNameNotSameException(String message) {
		super(message);
	}
}
