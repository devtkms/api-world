package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

/**
 * Service interface for managing user-related operations.
 */
public interface GetUserService {

    /**
     * Retrieves user information by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a GetUserResponseDto containing the user's information
     */
    GetUserResponseDto getUser(Long userId);
}