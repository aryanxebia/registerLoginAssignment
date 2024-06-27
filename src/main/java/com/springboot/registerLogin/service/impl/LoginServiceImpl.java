package com.springboot.registerLogin.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.UserLoginRequestDto;
import com.springboot.registerLogin.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private static final int MAX_ATTEMPTS = 5;
	private static final int BLOCK_DURATION_HOURS = 24;

	private final RegisterUserRepository registerUserRepository;

	@Autowired
	public LoginServiceImpl(RegisterUserRepository registerUserRepository) {
		this.registerUserRepository = registerUserRepository;
	}

	@Override
	public String loginUser(UserLoginRequestDto userLoginRequestDto) throws RegisterLoginException {
		Optional<Users> optionalUser = registerUserRepository.findByEmail(userLoginRequestDto.getEmail());
		if (!optionalUser.isPresent()) {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST, "User not present");
		}

		Users user = optionalUser.get();

		if (user.isBlocked()) {
			if (!user.getBlockedDate().plusHours(BLOCK_DURATION_HOURS).isBefore(LocalDateTime.now())) {
				throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "User is blocked");

			}
			user.setBlocked(false);
			user.setNumberOfAttempts(0);
			user.setBlockedDate(null);
		}
		if (!userLoginRequestDto.getPassword().equals(user.getPassword())) {

			user.setNumberOfAttempts(user.getNumberOfAttempts() + 1);
			if (user.getNumberOfAttempts() >= MAX_ATTEMPTS) {
				user.setBlocked(true);
				user.setBlockedDate(LocalDateTime.now());
			}
			registerUserRepository.save(user);
			throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");

		}
		user.setNumberOfAttempts(0);
		registerUserRepository.save(user);
		return "User logged in successfully.";

	}
}