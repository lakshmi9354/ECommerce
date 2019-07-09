package com.hcl.ecommerce.exception;

public class PasswordMismatchException extends RuntimeException {

	private static final long serialVersionUID = -6102272360036084147L;

	public PasswordMismatchException(String message) {
		super(message);
	}
}
