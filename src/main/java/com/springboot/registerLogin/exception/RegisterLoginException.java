package com.springboot.registerLogin.exception;

import org.springframework.http.HttpStatus;

public class RegisterLoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6578068681309495083L;
	private HttpStatus httpStatus;

	public RegisterLoginException() {
		super();
	}
	public RegisterLoginException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

   
}
