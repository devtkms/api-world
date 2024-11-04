package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

public interface UserManagementService {

        /**
         * Registers a new user.
         *
         * @param request the user registration request containing the user's information
         * @return a RegisterUserResponseDto containing the registered user's information
         */
        RegisterUserResponseDto registerUser(RegisterUserRequestDto request);

        /**
         * Retrieves user information by user ID.
         *
         * @param userId the ID of the user to retrieve
         * @return a GetUserResponseDto containing the user's information
         */
        GetUserResponseDto getUser(Long userId);

        /**
         * Updates user information.
         *
         * @param request the request containing the updated user information
         * @return an UpdateUserResponseDto containing the updated user's information
         */
        UpdateUserResponseDto updateUser(UpdateUserRequestDto request);

        /**
         * Deletes a user by user ID.
         *
         * @param userId the ID of the user to delete
         */
        void deleteUser(Long userId);
    }
