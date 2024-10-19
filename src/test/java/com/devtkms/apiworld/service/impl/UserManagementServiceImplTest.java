package com.devtkms.apiworld.service.impl;

import com.devtkms.apiworld.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.entity.UsersEntity;
import com.devtkms.apiworld.repository.UserManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public class UserManagementServiceImplTest {

    @Mock
    private UserManagementRepository userManagementRepository;

    @InjectMocks
    private UserManagementServiceImpl userManagementService;

    private BCryptPasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testRegisterUser() {

        RegisterUserRequestDto userRequestDto = new RegisterUserRequestDto();
        userRequestDto.setUserName("test");
        userRequestDto.setEmail("test@gmail.com");
        userRequestDto.setPassword("password");

        UsersEntity expectedUser = new UsersEntity();
        expectedUser.setUserId(1L);
        expectedUser.setUserName(userRequestDto.getUserName());
        expectedUser.setEmail(userRequestDto.getEmail());
        expectedUser.setPassword(encoder.encode(userRequestDto.getPassword()));

        doNothing().when(userManagementRepository).insertUser(any(UsersEntity.class));

        RegisterUserResponseDto responseDto = userManagementService.registerUser(userRequestDto);

        assertEquals(expectedUser.getUserName(), responseDto.getUserName(), "Username should be equal");
        assertEquals(expectedUser.getEmail(), responseDto.getEmail(), "Email should be equal");
    }
}