package com.springboot.registerLogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.request.UserLoginRequestDto;
import com.springboot.registerLogin.request.UserRegisterationRequestDto;

import com.springboot.registerLogin.service.RegisterationService;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/")
@RestController
public class RegistrationController {

	private final RegisterationService registerationService;

	@Autowired
	public RegistrationController(RegisterationService registerationService) {
		super();
		this.registerationService = registerationService;
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(
			 @org.springframework.web.bind.annotation.RequestBody UserRegisterationRequestDto userRegistrationRequestDto) {
		String response = registerationService.registerUser(userRegistrationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = registerationService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	
	 @PostMapping("/login")
	    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginDto) {
	        String response = registerationService.loginUser(userLoginDto);
	        if (response.equals("User logged in successfully.")) {
	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.status(401).body(response);
	        }
	    }
}
