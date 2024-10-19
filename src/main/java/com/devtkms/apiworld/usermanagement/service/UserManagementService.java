package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;

public interface UserManagementService {
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);
}
