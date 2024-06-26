package com.springboot.registerLogin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.request.PasswordResetDto;
import com.springboot.registerLogin.request.UserLoginRequestDto;
import com.springboot.registerLogin.request.UserRegisterationRequestDto;
import com.springboot.registerLogin.service.RegisterationService;

@Service
public class RegisterationServiceImpl implements RegisterationService {

	private static final int MAX_ATTEMPTS = 5;
	private static final int BLOCK_DURATION_HOURS = 24;

	private final RegisterUserRepository registerUserRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public RegisterationServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
		super();
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public String registerUser(UserRegisterationRequestDto userRegistrationRequestDto) {
//		System.out.println("User email coming as data: " + userRegistrationRequestDto.getEmail()); // Debugging
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
//		System.out.println("Mapped User Email: " + user.getEmail()); // Debugging
		registerUserRepository.save(user);
		return "User has been created.";
	}

	@Override
	public List<Users> getAllUsers() {
		return registerUserRepository.findAll();
	}

	public String loginUser(UserLoginRequestDto userLoginRequestDto) {
		Optional<Users> optionalUser = registerUserRepository.findByEmail(userLoginRequestDto.getEmail());
		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();
			if (user.isBlocked()) {
				if (user.getBlockedDate().plusHours(BLOCK_DURATION_HOURS).isBefore(LocalDateTime.now())) {
					// Unblock the user after block duration
					user.setBlocked(false);
					user.setNumberOfAttempts(0);
					user.setBlockedDate(null);
				} else {
					return "User is blocked. Please try again later.";
				}
			}
			if (userLoginRequestDto.getPassword().equals(user.getPassword())) {
				// Reset attempts on successful login
				user.setNumberOfAttempts(0);
				registerUserRepository.save(user);
				return "User logged in successfully.";
			} else {
				// Increment attempts on failed login
				user.setNumberOfAttempts(user.getNumberOfAttempts() + 1);
				if (user.getNumberOfAttempts() >= MAX_ATTEMPTS) {
					user.setBlocked(true);
					user.setBlockedDate(LocalDateTime.now());
				}
				registerUserRepository.save(user);
				return "Invalid credentials.";
			}
		} else {
			return "User not found.";
		}
	}
	
	
	 @Override
	 public String resetPassword(PasswordResetDto passwordResetDto) {
	        Optional<Users> optionalUser = registerUserRepository.findByEmail(passwordResetDto.getEmail());
	        if (optionalUser.isPresent()) {
	            Users user = optionalUser.get();
	            if (passwordResetDto.getCurrentPassword().equals(user.getPassword())) {
	                user.setPassword(passwordResetDto.getNewPassword());
	                registerUserRepository.save(user);
	                return "Password has been reset successfully.";
	            } else {
	                return "Current password is incorrect.";
	            }
	        } else {
	            return "User not found.";
	        }
	    }
}
