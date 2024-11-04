package com.devtkms.apiworld.usermanagement.service.impl;

import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.exception.UserManagementException;
import com.devtkms.apiworld.usermanagement.repository.UserManagementRepository;
import com.devtkms.apiworld.usermanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service implementation for managing user-related operations.
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user with the provided details.
     *
     * @param request the user registration request containing user details
     * @return a RegisterUserResponseDto containing the registered user's ID
     * @throws UserManagementException if the username or password is null or empty
     */
    @Override
    @Transactional
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UsersEntity user = new UsersEntity();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);

        try {
            userManagementRepository.insertUser(user);

            RegisterUserResponseDto response = new RegisterUserResponseDto();
            response.setUserId(user.getUserId());

            return response;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user", e);
        }
    }

    /**
     * Retrieves user information by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a GetUserResponseDto containing the user's information
     * @throws ResponseStatusException if the user retrieval fails
     */
    @Override
    public GetUserResponseDto getUser(Long userId) {
        try {
            UsersEntity user = userManagementRepository.selectUser(userId);

            GetUserResponseDto response = new GetUserResponseDto();
            response.setUserId(user.getUserId());
            response.setUserName(user.getUserName());
            response.setEmail(user.getEmail());

            return response;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve user", e);
        }
    }

    /**
     * Updates user information.
     *
     * @param request the request containing the updated user information
     * @return an UpdateUserResponseDto containing the updated user's information
     * @throws UserManagementException if the user ID, username, or password is invalid
     */
    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto request) {

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UsersEntity user = new UsersEntity();
        user.setUserId(request.getUserId());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);

        try {
            userManagementRepository.updateUser(user);

            UpdateUserResponseDto response = new UpdateUserResponseDto();
            response.setUserId(user.getUserId());
            response.setUserName(user.getUserName());
            response.setEmail(user.getEmail());
            response.setPassword(user.getPassword());

            return response;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user", e);
        }
    }

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user to delete
     * @throws ResponseStatusException if the user deletion fails
     */
    @Override
    public void deleteUser(Long userId) {
        try {
            userManagementRepository.deleteUser(userId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete user", e);
        }
    }
}