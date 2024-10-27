package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

/**
 * Service interface for managing user-related operations.
 */
public interface RegisterUserService {

    /**
     * Registers a new user.
     *
     * @param registerUserRequestDto the user registration request containing the user's information
     * @return a RegisterUserResponseDto containing the registered user's information
     */
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);
}