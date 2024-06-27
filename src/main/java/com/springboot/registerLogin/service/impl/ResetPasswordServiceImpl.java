package com.springboot.registerLogin.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.ResetPasswordDto;
import com.springboot.registerLogin.request.VerifyOtpDto;
import com.springboot.registerLogin.service.ResetPasswordService;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
	private final RegisterUserRepository registerUserRepository;

	@Autowired
	public ResetPasswordServiceImpl(RegisterUserRepository registerUserRepository) {
		this.registerUserRepository = registerUserRepository;
	}

	@Override
	public String verifyOtp(VerifyOtpDto verifyOtpDto) throws RegisterLoginException {

		Optional<Users> optionalUser = registerUserRepository.findByEmail(verifyOtpDto.getEmail());
		if (!optionalUser.isPresent()) {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST, "User not present");
		}

		Users user = optionalUser.get();

		if (user.isBlocked()) {
			throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "User is blocked");
		}

		if (user.getOtpValue() == null || LocalDateTime.now().isAfter(user.getOtpGenerationTime().plusMinutes(5))) {
			throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "Invalid Otp (Exceeded 5 minutes)");

		}
		if (!user.getOtpValue().equals(verifyOtpDto.getOtp())) {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST, "Incorrect Otp");

		}
		user.setOtpVerified(true);
		registerUserRepository.save(user);
		return "Otp has been verified";
	}

	@Override
	public String changePasswordWithOtp(ResetPasswordDto newPassword) throws RegisterLoginException {
		Optional<Users> optionalUser = registerUserRepository.findByEmail(newPassword.getEmail());
		if (!optionalUser.isPresent()) {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST, "User not present");
		}

		Users user = optionalUser.get();

		if (user.isBlocked()) {
			throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "User is blocked");
		}

		user.setPassword(newPassword.getPassword());
		user.setOtpValue(null);
		user.setOtpGenerationTime(null);
		registerUserRepository.save(user);

		return "Password updated";
	}

}
