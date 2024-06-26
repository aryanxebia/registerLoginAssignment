package com.springboot.registerLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.registerLogin.request.UserLoginRequestDto;
import com.springboot.registerLogin.service.LoginService;
import jakarta.validation.Valid;

@RequestMapping("/api/v1/")
@RestController
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginDto) {
		String response = loginService.loginUser(userLoginDto);
		if (response.equals("User logged in successfully.")) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(401).body(response);
		}
	}

}
