package com.springboot.registerLogin.service;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.GenerateOtpDto;

public interface GenerateOtpService {

	String generateOtp(GenerateOtpDto email) throws RegisterLoginException;

}
