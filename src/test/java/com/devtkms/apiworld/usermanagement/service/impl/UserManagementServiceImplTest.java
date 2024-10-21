package com.devtkms.apiworld.usermanagement.service.impl;

import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.repository.UserManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagementServiceImplTest {

    @Mock
    private UserManagementRepository userManagementRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserManagementServiceImpl userManagementService;

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

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(1L);
        userEntity.setUserName("testUser");
        userEntity.setEmail("test@example.com");

        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        doAnswer(invocation -> {
            UsersEntity entity = invocation.getArgument(0);
            entity.setUserId(1L);
            return null;
        }).when(userManagementRepository).insertUser(any(UsersEntity.class));

        RegisterUserResponseDto responseDto = userManagementService.registerUser(requestDto);

        assertEquals(1L, responseDto.getUserId());
        verify(userManagementRepository, times(1)).insertUser(any(UsersEntity.class));
        verify(passwordEncoder, times(1)).encode("password123");
    }

    @Test
    void testRegisterUser_ThrowsException_WhenUserNameIsNull() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName(null);
        requestDto.setPassword("password123");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementService.registerUser(requestDto);
        });

        assertEquals("User name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testRegisterUser_ThrowsException_WhenPasswordIsEmpty() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setPassword("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userManagementService.registerUser(requestDto);
        });

        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    @Test
    void testRegisterUser_PasswordIsHashed() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setPassword("password123");

        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(1L);

        doAnswer(invocation -> {
            UsersEntity user = invocation.getArgument(0);
            user.setUserId(1L);
            return null;
        }).when(userManagementRepository).insertUser(any(UsersEntity.class));

        RegisterUserResponseDto responseDto = userManagementService.registerUser(requestDto);


        assertEquals(1L, responseDto.getUserId());

        verify(passwordEncoder, times(1)).encode("password123");

        verify(userManagementRepository).insertUser(argThat(user ->
                "hashedPassword".equals(user.getPassword()) && "testUser".equals(user.getUserName())
        ));
    }
}