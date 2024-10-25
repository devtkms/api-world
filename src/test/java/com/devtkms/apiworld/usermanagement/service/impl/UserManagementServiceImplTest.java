package com.devtkms.apiworld.usermanagement.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.repository.UserManagementRepository;

public class UserManagementServiceImplTest {

    @InjectMocks
    private UserManagementServiceImpl userManagementService;

    @Mock
    private UserManagementRepository userManagementRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testuser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password123");

        String hashedPassword = "hashedPassword";
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(hashedPassword);

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(1L);

        doAnswer(invocation -> {
            UsersEntity user = invocation.getArgument(0);
            user.setUserId(1L);
            return null;
        }).when(userManagementRepository).insertUser(any(UsersEntity.class));

        RegisterUserResponseDto responseDto = userManagementService.registerUser(requestDto);

        assertNotNull(responseDto);
        assertEquals(1L, responseDto.getUserId());
    }

    @Test
    public void testRegisterUser_DatabaseError() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testuser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password123");

        String hashedPassword = "hashedPassword";
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(hashedPassword);

        doThrow(new RuntimeException("Database error"))
                .when(userManagementRepository).insertUser(any(UsersEntity.class));

        assertThrows(ResponseStatusException.class, () -> userManagementService.registerUser(requestDto));
    }
}