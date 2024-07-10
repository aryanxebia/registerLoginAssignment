package com.springboot.registerLogin.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.ChangePasswordDto;
import com.springboot.registerLogin.service.ChangePasswordService;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

	private final RegisterUserRepository registerUserRepository;

	@Autowired
	public ChangePasswordServiceImpl(RegisterUserRepository registerUserRepository) {
		this.registerUserRepository = registerUserRepository;
	}

	@Override
	public String changePassword(ChangePasswordDto changePasswordDto) throws RegisterLoginException {
		// TODO Auto-generated method stub
		Optional<Users> optionalUser = registerUserRepository.findByEmail(changePasswordDto.getEmail());
		if (!optionalUser.isPresent()) {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST, "User not found.");
		}
		Users user = optionalUser.get();
		if (!changePasswordDto.getCurrentPassword().equals(user.getPassword())) {
			throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "Current password is incorrect.");

		}
		user.setPassword(changePasswordDto.getNewPassword());
		registerUserRepository.save(user);
		return "Password has been changed successfully.";
	}
}
