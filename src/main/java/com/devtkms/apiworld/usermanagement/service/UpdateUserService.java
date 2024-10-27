package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

/**
 * Service interface for managing user-related operations.
 */
public interface UpdateUserService {

    /**
     * Updates user information.
     *
     * @param updateUserRequestDto the request containing the updated user information
     * @return an UpdateUserResponseDto containing the updated user's information
     */
    UpdateUserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto);
}