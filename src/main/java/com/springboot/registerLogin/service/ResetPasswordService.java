package com.springboot.registerLogin.service;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.PasswordResetDto;

public interface ResetPasswordService {
	String resetPassword(PasswordResetDto passwordResetDto) throws RegisterLoginException;
}
