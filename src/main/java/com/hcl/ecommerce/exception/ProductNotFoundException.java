package com.hcl.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8599383035561257623L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
