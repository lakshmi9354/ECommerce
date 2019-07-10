package com.hcl.ecommerce.exception;

public class RolesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6442154940758625660L;
	
	public RolesNotFoundException(String message) {
		super(message);
	}

}
