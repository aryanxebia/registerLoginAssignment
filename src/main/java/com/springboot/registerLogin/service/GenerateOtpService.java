package com.springboot.registerLogin.service;

import com.springboot.registerLogin.exception.RegisterLoginException;

public interface GenerateOtpService {

	String generateOtp(String email) throws RegisterLoginException;

}
