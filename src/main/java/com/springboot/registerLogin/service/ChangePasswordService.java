package com.springboot.registerLogin.service;

import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.ChangePasswordDto;

public interface ChangePasswordService {
	String changePassword(ChangePasswordDto changePasswordDto) throws RegisterLoginException;
}
