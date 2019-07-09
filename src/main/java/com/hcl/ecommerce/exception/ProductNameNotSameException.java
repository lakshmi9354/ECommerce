package com.hcl.ecommerce.exception;

public class ProductNameNotSameException extends RuntimeException {
	private static final long serialVersionUID = 192905058156286318L;

	public ProductNameNotSameException(String message) {
		super(message);
	}
}
