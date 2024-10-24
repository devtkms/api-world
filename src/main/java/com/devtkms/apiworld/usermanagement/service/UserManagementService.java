package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

public interface UserManagementService {
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);

    GetUserResponseDto getUser(Long userId);

    UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto);

    void deleteUser(Long userId);
}
