package com.devtkms.apiworld.usermanagement.service.impl;

import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.exception.UserManagementException;
import com.devtkms.apiworld.usermanagement.repository.UserManagementRepository;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {
        if (registerUserRequestDto.getUserName() == null || registerUserRequestDto.getUserName().isEmpty()) {
            throw new UserManagementException("User name cannot be null or empty");
        }

        if (registerUserRequestDto.getPassword() == null || registerUserRequestDto.getPassword().isEmpty()) {
            throw new UserManagementException("Password cannot be null or empty");
        }

        String hashedPassword = passwordEncoder.encode(registerUserRequestDto.getPassword());

        UsersEntity user = new UsersEntity();
        user.setUserName(registerUserRequestDto.getUserName());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setPassword(hashedPassword);

        try {
            userManagementRepository.insertUser(user);

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        responseDto.setUserId(user.getUserId());

        return responseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user", e);
        }
    }

    @Override
    public GetUserResponseDto getUser(Long userId) {

        try {
            UsersEntity user = userManagementRepository.selectUser(userId);

        GetUserResponseDto responseDto = new GetUserResponseDto();
        responseDto.setUserId(user.getUserId());
        responseDto.setUserName(user.getUserName());
        responseDto.setEmail(user.getEmail());

        return responseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user", e);
        }
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto) {

        if (updateUserRequestDto.getUserId() == null) {
            throw new UserManagementException("User id cannot be null");
        }

        if (updateUserRequestDto.getUserName() == null || updateUserRequestDto.getUserName().isEmpty()) {
            throw new UserManagementException("User name cannot be null or empty");
        }

        if (updateUserRequestDto.getPassword() == null || updateUserRequestDto.getPassword().isEmpty()) {
            throw new UserManagementException("Password cannot be null or empty");
        }

        String hashedPassword = passwordEncoder.encode(updateUserRequestDto.getPassword());

        UsersEntity user = new UsersEntity();
        user.setUserId(updateUserRequestDto.getUserId());
        user.setUserName(updateUserRequestDto.getUserName());
        user.setEmail(updateUserRequestDto.getEmail());
        user.setPassword(hashedPassword);

        try {
            userManagementRepository.updateUser(user);

            UpdateUserResponseDto responseDto = new UpdateUserResponseDto();
            responseDto.setUserId(user.getUserId());
            responseDto.setUserName(user.getUserName());
            responseDto.setEmail(user.getEmail());
            responseDto.setPassword(user.getPassword());

            return responseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user", e);
        }
    }

    @Override
    public void deleteUser(Long userId) {

        try {
            userManagementRepository.deleteUser(userId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete user", e);
        }
    }
}