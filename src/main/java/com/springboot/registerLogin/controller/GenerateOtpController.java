package com.springboot.registerLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.GenerateOtpDto;
import com.springboot.registerLogin.service.GenerateOtpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class GenerateOtpController {

	private final GenerateOtpService generateOtpService;

	@Autowired
	public GenerateOtpController(GenerateOtpService generateOtpService) {
		this.generateOtpService = generateOtpService;
	}

	@GetMapping("/generate-otp")
	public ResponseEntity<String> resetPassword(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody GenerateOtpDto resetPasswordOtp)
			throws RegisterLoginException {

		String response = generateOtpService.generateOtp(resetPasswordOtp);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}