package com.springboot.registerLogin.service;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.ResetPasswordDto;
import com.springboot.registerLogin.request.VerifyOtpDto;

public interface ResetPasswordService {

	String verifyOtp(VerifyOtpDto verifyOtpDto) throws RegisterLoginException;

	String changePasswordWithOtp(ResetPasswordDto newPassword) throws RegisterLoginException;
}
