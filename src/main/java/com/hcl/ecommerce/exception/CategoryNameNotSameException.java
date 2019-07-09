package com.hcl.ecommerce.exception;

public class CategoryNameNotSameException extends RuntimeException {

	private static final long serialVersionUID = 1442550981114483599L;
	
	public CategoryNameNotSameException(String message) {
		super(message);
	}
	
}
