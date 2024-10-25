package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagementControllerTest {

    @InjectMocks
    private UserManagementController userManagementController;

    @Mock
    private UserManagementService userManagementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidRequest_ReturnsSuccess() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("securePassword");

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        responseDto.setUserId(1L);
        when(userManagementService.registerUser(requestDto)).thenReturn(responseDto);

        // コントローラーのメソッドを呼び出す
        ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> response = userManagementController.registerUser(requestDto);

        // 結果のアサーション
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertEquals(responseDto, response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    void registerUser_InvalidRequest_ReturnsBadRequest() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("");
        requestDto.setEmail("invalid-email");
        requestDto.setPassword("short");

        when(userManagementService.registerUser(requestDto)).thenThrow(new IllegalArgumentException("Invalid request"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.registerUser(requestDto);
        });

        assertEquals("Invalid request", exception.getMessage());
    }

    @Test
    void getUserById_ExistingUser_ReturnsUser() {
        Long userId = 1L;

        GetUserResponseDto responseDto = new GetUserResponseDto(userId, "testUser", "test@example.com");
        when(userManagementService.getUser(userId)).thenReturn(responseDto);

        ResponseEntity<ApiResponseDto<GetUserResponseDto>> response = userManagementController.getUserById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertEquals(responseDto, response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    void getUserById_NonExistingUser_ReturnsNotFound() {
        Long userId = 999L;

        when(userManagementService.getUser(userId)).thenThrow(new IllegalArgumentException("User not found"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.getUserById(userId);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void updateUser_ValidRequest_ReturnsSuccess() {
        Long userId = 1L;

        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(userId);
        requestDto.setUserName("updatedUser");
        requestDto.setEmail("updated@example.com");
        requestDto.setPassword("newSecurePassword");

        UpdateUserResponseDto responseDto = new UpdateUserResponseDto(userId, "updatedUser", "updated@example.com", "newSecurePassword");
        when(userManagementService.updateUser(requestDto)).thenReturn(responseDto);

        ResponseEntity<ApiResponseDto<UpdateUserResponseDto>> response = userManagementController.updateUser(requestDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertEquals(responseDto, response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    void updateUser_InvalidRequest_ReturnsBadRequest() {
        Long userId = 1L;

        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(userId);
        requestDto.setUserName("");
        requestDto.setEmail("invalid-email");
        requestDto.setPassword("short");

        when(userManagementService.updateUser(requestDto)).thenThrow(new IllegalArgumentException("Invalid request"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.updateUser(requestDto);
        });

        assertEquals("Invalid request", exception.getMessage());
    }

    @Test
    void deleteUser_ExistingUser_ReturnsSuccess() {
        Long userId = 1L;

        doNothing().when(userManagementService).deleteUser(userId);

        ResponseEntity<ApiResponseDto<Void>> response = userManagementController.deleteUser(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().getStatus());
        assertNull(response.getBody().getData());
        assertEquals("Request completed successfully", response.getBody().getMessage());
        assertEquals(200, response.getBody().getCode());
    }

    @Test
    void deleteUser_NonExistingUser_ReturnsNotFound() {
        Long userId = 999L;

        doThrow(new IllegalArgumentException("User not found")).when(userManagementService).deleteUser(userId);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementController.deleteUser(userId);
        });

        assertEquals("User not found", exception.getMessage());
    }
}