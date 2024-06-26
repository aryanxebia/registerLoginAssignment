package com.springboot.registerLogin.service;

import java.util.List;

import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.request.UserRegisterationRequestDto;

public interface RegisterationService {
	String registerUser(UserRegisterationRequestDto userRegistrationRequestDto);

	List<Users> getAllUsers();

//	String loginUser(UserLoginRequestDto userLoginRequestDto);

//	String resetPassword(PasswordResetDto passwordResetDto);
}
