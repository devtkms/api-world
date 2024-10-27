package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.service.DeleteUserService;
import com.devtkms.apiworld.usermanagement.service.GetUserService;
import com.devtkms.apiworld.usermanagement.service.RegisterUserService;
import com.devtkms.apiworld.usermanagement.service.UpdateUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagementControllerTest {

    @InjectMocks
    private UserManagementController userManagementController; // Controller to be tested

    @Mock
    RegisterUserService registerUserService;

    @Mock
    GetUserService getUserService;

    @Mock
    UpdateUserService updateUserService;

    @Mock
    DeleteUserService deleteUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    /**
     * Tests the user registration process with valid request data.
     * It should return a successful response containing the registered user's information.
     */
    @Test
    void registerUser_ValidRequest_ReturnsSuccess() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("securePassword");

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        responseDto.setUserId(1L);
        when(registerUserService.registerUser(requestDto)).thenReturn(responseDto); // Mock service response

        ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> response = userManagementController.registerUser(requestDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertEquals(responseDto, response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    /**
     * Tests the user registration process with invalid request data.
     * It should throw an IllegalArgumentException.
     */
    @Test
    void registerUser_InvalidRequest_ReturnsBadRequest() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("");
        requestDto.setEmail("invalid-email");
        requestDto.setPassword("short");

        when(registerUserService.registerUser(requestDto)).thenThrow(new IllegalArgumentException("Invalid request")); // Mock exception

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.registerUser(requestDto); // Expecting an exception
        });

        assertEquals("Invalid request", exception.getMessage()); // Verify the exception message
    }

    /**
     * Tests retrieving user information by user ID for an existing user.
     * It should return a successful response containing the user's details.
     */
    @Test
    void getUserById_ExistingUser_ReturnsUser() {
        Long userId = 1L;

        GetUserResponseDto responseDto = new GetUserResponseDto(userId, "testUser", "test@example.com");
        when(getUserService.getUser(userId)).thenReturn(responseDto); // Mock service response

        ResponseEntity<ApiResponseDto<GetUserResponseDto>> response = userManagementController.getUserById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertEquals(responseDto, response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    /**
     * Tests retrieving user information by user ID for a non-existing user.
     * It should throw an IllegalArgumentException.
     */
    @Test
    void getUserById_NonExistingUser_ReturnsNotFound() {
        Long userId = 999L;

        when(getUserService.getUser(userId)).thenThrow(new IllegalArgumentException("User not found")); // Mock exception

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.getUserById(userId); // Expecting an exception
        });

        assertEquals("User not found", exception.getMessage()); // Verify the exception message
    }

    /**
     * Tests the user update process with valid request data.
     * It should return a successful response containing the updated user's information.
     */
    @Test
    void updateUser_ValidRequest_ReturnsSuccess() {
        Long userId = 1L;

        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(userId);
        requestDto.setUserName("updatedUser");
        requestDto.setEmail("updated@example.com");
        requestDto.setPassword("newSecurePassword");

        UpdateUserResponseDto responseDto = new UpdateUserResponseDto(userId, "updatedUser", "updated@example.com", "newSecurePassword");
        when(updateUserService.updateUser(requestDto)).thenReturn(responseDto); // Mock service response

        ResponseEntity<ApiResponseDto<UpdateUserResponseDto>> response = userManagementController.updateUser(requestDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertEquals(responseDto, response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    /**
     * Tests the user update process with invalid request data.
     * It should throw an IllegalArgumentException.
     */
    @Test
    void updateUser_InvalidRequest_ReturnsBadRequest() {
        Long userId = 1L;

        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(userId);
        requestDto.setUserName("");
        requestDto.setEmail("invalid-email");
        requestDto.setPassword("short");

        when(updateUserService.updateUser(requestDto)).thenThrow(new IllegalArgumentException("Invalid request")); // Mock exception

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.updateUser(requestDto); // Expecting an exception
        });

        assertEquals("Invalid request", exception.getMessage()); // Verify the exception message
    }

    /**
     * Tests the user deletion process for an existing user.
     * It should return a success response with no data.
     */
    @Test
    void deleteUser_ExistingUser_ReturnsSuccess() {
        Long userId = 1L;

        doNothing().when(deleteUserService).deleteUser(userId); // Mock successful deletion

        ResponseEntity<ApiResponseDto<Void>> response = userManagementController.deleteUser(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertNull(response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    /**
     * Tests the user deletion process for a non-existing user.
     * It should throw an IllegalArgumentException.
     */
    @Test
    void deleteUser_NonExistingUser_ReturnsNotFound() {
        Long userId = 999L;

        doThrow(new IllegalArgumentException("User not found")).when(deleteUserService).deleteUser(userId); // Mock exception

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.deleteUser(userId); // Expecting an exception
        });

        assertEquals("User not found", exception.getMessage()); // Verify the exception message
    }
}