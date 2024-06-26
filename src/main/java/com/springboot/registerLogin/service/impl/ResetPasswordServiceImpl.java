package com.springboot.registerLogin.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.exception.RegisterLoginException;
import com.springboot.registerLogin.request.PasswordResetDto;
import com.springboot.registerLogin.service.ResetPasswordService;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

	private final RegisterUserRepository registerUserRepository;

	@Autowired
	public ResetPasswordServiceImpl(RegisterUserRepository registerUserRepository) {
		this.registerUserRepository = registerUserRepository;
	}

	@Override
	public String resetPassword(PasswordResetDto passwordResetDto) throws RegisterLoginException {
		Optional<Users> optionalUser = registerUserRepository.findByEmail(passwordResetDto.getEmail());
		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();
			if (passwordResetDto.getCurrentPassword().equals(user.getPassword())) {
				user.setPassword(passwordResetDto.getNewPassword());
				registerUserRepository.save(user);
				return "Password has been reset successfully.";
			} else {
				throw new RegisterLoginException(HttpStatus.UNAUTHORIZED, "Current password is incorrect.");
			}
		} else {
			throw new RegisterLoginException(HttpStatus.BAD_REQUEST,"User not found.");
		}
	}
}
