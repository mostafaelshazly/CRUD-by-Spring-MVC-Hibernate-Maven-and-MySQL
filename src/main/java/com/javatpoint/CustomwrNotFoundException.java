package com.javatpoint;

public class CustomwrNotFoundException extends RuntimeException{

	public CustomwrNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomwrNotFoundException(String message) {
		super(message);
	}

	public CustomwrNotFoundException(Throwable cause) {
		super(cause);
	}

}
