package com.springboot.registerLogin.service;

import com.springboot.registerLogin.request.UserLoginRequestDto;

public interface LoginService {
	String loginUser(UserLoginRequestDto userLoginRequestDto);

}
