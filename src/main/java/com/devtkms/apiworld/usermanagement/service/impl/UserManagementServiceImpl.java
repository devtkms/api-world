package com.devtkms.apiworld.usermanagement.service.impl;

import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.repository.UserManagementRepository;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {

        if (registerUserRequestDto.getUserName() == null || registerUserRequestDto.getUserName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }

        if (registerUserRequestDto.getPassword() == null || registerUserRequestDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        String hashedPassword = passwordEncoder.encode(registerUserRequestDto.getPassword());


        UsersEntity user = new UsersEntity();
        user.setUserName(registerUserRequestDto.getUserName());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setPassword(hashedPassword);

        userManagementRepository.insertUser(user);

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        responseDto.setUserId(user.getUserId());
        responseDto.setMessage("User registered successfully");

        return responseDto;
    }
}