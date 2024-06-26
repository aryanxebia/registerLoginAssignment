package com.springboot.registerLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.PasswordResetDto;
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

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto) throws RegisterLoginException {
		return ResponseEntity.ok(resetPasswordService.resetPassword(passwordResetDto));
	}
}
