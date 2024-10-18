package com.devtkms.apiworld.service;

import com.devtkms.apiworld.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.dto.RegisterUserResponseDto;

public interface UserManagementService {
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);
}
