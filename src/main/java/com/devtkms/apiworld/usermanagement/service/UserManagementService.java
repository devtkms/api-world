package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

/**
 * Service interface for managing user-related operations.
 */
public interface UserManagementService {

    /**
     * Registers a new user.
     *
     * @param registerUserRequestDto the user registration request containing the user's information
     * @return a RegisterUserResponseDto containing the registered user's information
     */
    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);

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
     * @param updateUserRequestDto the request containing the updated user information
     * @return an UpdateUserResponseDto containing the updated user's information
     */
    UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto);

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUser(Long userId);
}