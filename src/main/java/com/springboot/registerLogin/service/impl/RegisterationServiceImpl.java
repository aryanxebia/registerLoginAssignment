package com.springboot.registerLogin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.registerLogin.dao.RegisterUserRepository;
import com.springboot.registerLogin.entity.Users;
import com.springboot.registerLogin.request.UserRegisterationRequestDto;
import com.springboot.registerLogin.service.RegisterationService;

 
@Service
public class RegisterationServiceImpl implements RegisterationService {

    private final RegisterUserRepository registerUserRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RegisterationServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
        super();
        this.registerUserRepository = registerUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(  UserRegisterationRequestDto userRegistrationRequestDto) {
    	System.out.println("User email coming as data: " + userRegistrationRequestDto.getEmail());  // Debugging
        Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
        System.out.println("Mapped User Email: " + user.getEmail());  // Debugging
        registerUserRepository.save(user);
        return "User has been created.";
    }

	@Override
	public List<Users> getAllUsers() {
		 return registerUserRepository.findAll();
	}
}
