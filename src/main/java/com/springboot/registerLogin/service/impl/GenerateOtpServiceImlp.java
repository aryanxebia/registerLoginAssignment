package com.springboot.registerLogin.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.GenerateOtpDto;
import com.springboot.registerLogin.service.GenerateOtpService;

@Service
public class GenerateOtpServiceImlp implements GenerateOtpService {
	public static String generateOTP() {
		SecureRandom random = new SecureRandom();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	private final RegisterUserRepository registerUserRepository;

	@Autowired
	public GenerateOtpServiceImlp(RegisterUserRepository registerUserRepository) {
		this.registerUserRepository = registerUserRepository;
	}

	@Override
	public String generateOtp(GenerateOtpDto email) throws RegisterLoginException {

		Optional<Users> optionalUser = registerUserRepository.findByEmail(email.getEmail());
		if (!optionalUser.isPresent()) {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST, "User not found.");
		}
		Users user = optionalUser.get();
		String otp = generateOTP();

		user.setOtpValue(otp);
		user.setOtpGenerationTime(LocalDateTime.now());
		registerUserRepository.save(user);
		return otp;

	}

}
