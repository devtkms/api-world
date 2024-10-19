package com.devtkms.apiworld.service.impl;

import com.devtkms.apiworld.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.entity.UsersEntity;
import com.devtkms.apiworld.repository.UserManagementRepository;
import com.devtkms.apiworld.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Override
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(registerUserRequestDto.getPassword());

        UsersEntity user = new UsersEntity();
        user.setUserName(registerUserRequestDto.getUserName());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setPassword(hashedPassword);
        userManagementRepository.insertUser(user);

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        responseDto.setUserId(user.getUserId());
        responseDto.setUserName(user.getUserName());
        responseDto.setEmail(user.getEmail());

        return responseDto;
    }
}