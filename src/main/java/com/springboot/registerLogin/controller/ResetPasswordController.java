package com.springboot.registerLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.ResetPasswordDto;
import com.springboot.registerLogin.request.VerifyOtpDto;
import com.springboot.registerLogin.service.ResetPasswordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {

	private final ResetPasswordService resetPasswordService;

	@Autowired
	public ResetPasswordController(ResetPasswordService resetPasswordService) {
		this.resetPasswordService = resetPasswordService;
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<String> resetPassword(@Valid @RequestBody VerifyOtpDto verifyOtpDto)
			throws RegisterLoginException {
		return ResponseEntity.ok(resetPasswordService.verifyOtp(verifyOtpDto));
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody ResetPasswordDto newPassword)
			throws RegisterLoginException {
		return ResponseEntity.ok(resetPasswordService.changePasswordWithOtp(newPassword));
	}

}
