package com.devtkms.apiworld.usermanagement.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devtkms.apiworld.usermanagement.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.repository.UserManagementRepository;

public class UserManagementServiceImplTest {

    @InjectMocks
    private UserManagementServiceImpl userManagementService; // Service to be tested

    @Mock
    private UserManagementRepository userManagementRepository; // Mocked repository for user management

    @Mock
    private PasswordEncoder passwordEncoder; // Mocked password encoder for hashing passwords

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    /**
     * Tests the user registration process for a successful case.
     * It should return a response DTO containing the new user's ID.
     */
    @Test
    public void testRegisterUser_Success() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testuser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password123");

        String hashedPassword = "hashedPassword";
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(hashedPassword); // Mock password encoding

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(1L); // Set mocked user ID

        // Mock the insertUser method to set the user ID
        doAnswer(invocation -> {
            UsersEntity user = invocation.getArgument(0);
            user.setUserId(1L);
            return null;
        }).when(userManagementRepository).insertUser(any(UsersEntity.class));

        RegisterUserResponseDto responseDto = userManagementService.registerUser(requestDto); // Call the method under test

        assertNotNull(responseDto); // Assert that the response is not null
        assertEquals(1L, responseDto.getUserId()); // Assert that the user ID is as expected
    }

    /**
     * Tests the user registration process when a database error occurs.
     * It should throw a ResponseStatusException.
     */
    @Test
    public void testRegisterUser_DatabaseError() {
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName("testuser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password123");

        String hashedPassword = "hashedPassword";
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(hashedPassword); // Mock password encoding

        // Mock the insertUser method to throw a runtime exception
        doThrow(new RuntimeException("Database error"))
                .when(userManagementRepository).insertUser(any(UsersEntity.class));

        // Assert that a ResponseStatusException is thrown
        assertThrows(ResponseStatusException.class, () -> userManagementService.registerUser(requestDto));
    }

    /**
     * Tests retrieving user information by user ID for an existing user.
     * It should return a GetUserResponseDto containing the user's information.
     */
    @Test
    public void testGetUser_Success() {
        Long userId = 1L;

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(userId);
        userEntity.setUserName("testUser");
        userEntity.setEmail("test@example.com");

        when(userManagementRepository.selectUser(userId)).thenReturn(userEntity); // Mock repository response

        GetUserResponseDto responseDto = userManagementService.getUser(userId); // Call the method under test

        assertNotNull(responseDto); // Assert that the response is not null
        assertEquals(userId, responseDto.getUserId()); // Assert that the user ID matches
        assertEquals("testUser", responseDto.getUserName()); // Assert that the username matches
        assertEquals("test@example.com", responseDto.getEmail()); // Assert that the email matches
    }

    /**
     * Tests retrieving user information by user ID when the user does not exist.
     * It should throw a ResponseStatusException.
     */
    @Test
    public void testGetUser_NotFound() {
        Long userId = 999L; // Non-existing user ID

        when(userManagementRepository.selectUser(userId)).thenReturn(null); // Mock repository to return null

        // Assert that a ResponseStatusException is thrown
        assertThrows(ResponseStatusException.class, () -> userManagementService.getUser(userId));
    }

    /**
     * Tests updating user information with valid request data.
     * It should return a response DTO containing the updated user's information.
     */
    @Test
    public void testUpdateUser_Success() {
        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(1L);
        requestDto.setUserName("updatedUser");
        requestDto.setEmail("updated@example.com");
        requestDto.setPassword("newPassword");

        String hashedPassword = "hashedPassword";
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(hashedPassword); // Mock password encoding

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(1L);
        userEntity.setUserName("updatedUser");
        userEntity.setEmail("updated@example.com");
        userEntity.setPassword(hashedPassword);

        // Mock the updateUser method to do nothing (simulate successful update)
        doNothing().when(userManagementRepository).updateUser(any(UsersEntity.class));

        UpdateUserResponseDto responseDto = userManagementService.updateUser(requestDto); // Call the method under test

        assertNotNull(responseDto); // Assert that the response is not null
        assertEquals(1L, responseDto.getUserId()); // Assert that the user ID is as expected
        assertEquals("updatedUser", responseDto.getUserName()); // Assert that the username is updated
        assertEquals("updated@example.com", responseDto.getEmail()); // Assert that the email is updated
        assertEquals(hashedPassword, responseDto.getPassword()); // Assert that the password is hashed
    }

    /**
     * Tests updating user information when a database error occurs.
     * It should throw a ResponseStatusException.
     */
    @Test
    public void testUpdateUser_DatabaseError() {
        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(1L);
        requestDto.setUserName("updatedUser");
        requestDto.setEmail("updated@example.com");
        requestDto.setPassword("newPassword");

        String hashedPassword = "hashedPassword";
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(hashedPassword); // Mock password encoding

        // Mock the updateUser method to throw a runtime exception
        doThrow(new RuntimeException("Database error"))
                .when(userManagementRepository).updateUser(any(UsersEntity.class));

        // Assert that a ResponseStatusException is thrown
        assertThrows(ResponseStatusException.class, () -> userManagementService.updateUser(requestDto));
    }

    /**
     * Tests deleting a user by user ID for an existing user.
     * It should not throw any exceptions.
     */
    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L; // ID of the user to delete

        doNothing().when(userManagementRepository).deleteUser(userId); // Mock successful deletion

        assertDoesNotThrow(() -> userManagementService.deleteUser(userId)); // Assert that no exception is thrown
    }

    /**
     * Tests deleting a user by user ID when the user does not exist.
     * It should throw a ResponseStatusException.
     */
    @Test
    public void testDeleteUser_NotFound() {
        Long userId = 999L; // Non-existing user ID

        doThrow(new RuntimeException("User not found")).when(userManagementRepository).deleteUser(userId); // Mock exception

        // Assert that a ResponseStatusException is thrown
        assertThrows(ResponseStatusException.class, () -> userManagementService.deleteUser(userId));
    }
}