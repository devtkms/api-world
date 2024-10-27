package com.devtkms.apiworld.usermanagement.service;

import com.devtkms.apiworld.usermanagement.dto.*;

/**
 * Service interface for managing user-related operations.
 */
public interface DeleteUserService {

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUser(Long userId);
}