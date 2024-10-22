package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    void testRegisterUser_Success() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password123");

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto(1L);

        when(userManagementService.registerUser(any(RegisterUserRequestDto.class))).thenReturn(responseDto);

        ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> responseEntity =
                userManagementController.registerUser(requestDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("User registered successfully", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
        assertEquals(1L, responseEntity.getBody().getData().getUserId());
    }

    @Test
    void testRegisterUser_BadRequest() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();

        requestDto.setUserName("");

        doThrow(new IllegalArgumentException("Invalid data")).when(userManagementService).registerUser(any());


        ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> responseEntity =
                userManagementController.registerUser(requestDto);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("error", responseEntity.getBody().getStatus());
        assertEquals("Invalid data", responseEntity.getBody().getMessage());
    }

    @Test
    void testRegisterUser_InternalServerError() {

        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password123");

        doThrow(new RuntimeException("Unexpected error")).when(userManagementService).registerUser(any());


        ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> responseEntity =
                userManagementController.registerUser(requestDto);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("error", responseEntity.getBody().getStatus());
        assertEquals("An unexpected error occurred", responseEntity.getBody().getMessage());
    }
}