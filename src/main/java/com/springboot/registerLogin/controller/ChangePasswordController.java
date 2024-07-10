package com.springboot.registerLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.ChangePasswordDto;
import com.springboot.registerLogin.service.ChangePasswordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class ChangePasswordController {

	private final ChangePasswordService changePasswordService;

	@Autowired
	public ChangePasswordController(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	@PostMapping("/change-password")
	public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto)
			throws RegisterLoginException {
		return ResponseEntity.ok(changePasswordService.changePassword(changePasswordDto));
	}
}
