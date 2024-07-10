package com.springboot.registerLogin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.UserLoginRequestDto;
import com.springboot.registerLogin.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private static final int MAX_ATTEMPTS = 5;
	private static final int BLOCK_DURATION_HOURS = 24;

	private final RegisterUserRepository registerUserRepository;
	private final RestTemplate restTemplate;

	@Value("${banking.healthcheck.url}")
	private String healthCheckEndpointUrl;

	@Autowired
	public LoginServiceImpl(RegisterUserRepository registerUserRepository, RestTemplate restTemplate) {
		this.registerUserRepository = registerUserRepository;
		this.restTemplate = restTemplate;
	}

	@Override
	public String loginUser(UserLoginRequestDto userLoginRequestDto) throws RegisterLoginException {

		
		ResponseEntity<String> response = restTemplate.exchange(healthCheckEndpointUrl, HttpMethod.GET, null, String.class);;
		if(!response.getStatusCode().is2xxSuccessful()) {
			//new 
		}
		System.out.println("response to be printed :"+response.getBody());
		/*
		 * Optional<Users> optionalUser =
		 * registerUserRepository.findByEmail(userLoginRequestDto.getEmail()); if
		 * (!optionalUser.isPresent()) { throw new
		 * RegisterLoginException(HttpStatus.BAD_REQUEST, "User not present"); }
		 * 
		 * Users user = optionalUser.get();
		 * 
		 * if (user.isBlocked()) { if
		 * (!user.getBlockedDate().plusHours(BLOCK_DURATION_HOURS).isBefore(
		 * LocalDateTime.now())) { throw new
		 * RegisterLoginException(HttpStatus.UNAUTHORIZED, "User is blocked");
		 * 
		 * } user.setBlocked(false); user.setNumberOfAttempts(0);
		 * user.setBlockedDate(null); } if
		 * (!userLoginRequestDto.getPassword().equals(user.getPassword())) {
		 * 
		 * user.setNumberOfAttempts(user.getNumberOfAttempts() + 1); if
		 * (user.getNumberOfAttempts() >= MAX_ATTEMPTS) { user.setBlocked(true);
		 * user.setBlockedDate(LocalDateTime.now()); }
		 * registerUserRepository.save(user); throw new
		 * RegisterLoginException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
		 * 
		 * } user.setNumberOfAttempts(0); registerUserRepository.save(user);
		 */
		return "User logged in successfully.";

	}
}