package com.springboot.registerLogin.exception;

import org.springframework.http.HttpStatus;

public class RegisterLoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6578068681309495083L;
	private HttpStatus httpStatus;
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public RegisterLoginException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

   
}
