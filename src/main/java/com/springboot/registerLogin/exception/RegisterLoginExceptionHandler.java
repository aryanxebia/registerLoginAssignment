package com.springboot.registerLogin.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterLoginExceptionHandler {

	@ExceptionHandler(value = { RegisterLoginException.class })
	public ResponseEntity<Object> handleRegisterLoginException(RegisterLoginException registerLoginException) {
		return ResponseEntity.status(registerLoginException.getHttpStatus()).body(registerLoginException.getMessage());
	}
	
	

}
