package com.devtkms.apiworld.usermanagement.repository;

import com.devtkms.apiworld.usermanagement.dao.UsersDao;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserManagementRepositoryTest {

    @InjectMocks
    private UserManagementRepository userManagementRepository; // Repository to be tested

    @Mock
    private UsersDao usersDao; // Mocked DAO for user management

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    public void testInsertUser() {
        UsersEntity user = new UsersEntity();
        user.setUserId(1L);
        user.setUserName("TestUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        userManagementRepository.insertUser(user); // Call the method under test

        verify(usersDao, times(1)).insertUser(user); // Verify that the DAO method was called once
    }

    /**
     * Tests retrieving a user by user ID.
     * It should return the corresponding UsersEntity object.
     */
    @Test
    public void testSelectUser() {
        Long userId = 1L; // ID of the user to retrieve

        UsersEntity userEntity = new UsersEntity();
        userEntity.setUserId(userId);
        userEntity.setUserName("TestUser");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password123");

        when(usersDao.selectUser(userId)).thenReturn(userEntity); // Mock the DAO response

        UsersEntity retrievedUser = userManagementRepository.selectUser(userId); // Call the method under test

        assertNotNull(retrievedUser); // Assert that the retrieved user is not null
        assertEquals(userId, retrievedUser.getUserId()); // Assert that the user ID matches
        assertEquals("TestUser", retrievedUser.getUserName()); // Assert that the username matches
        assertEquals("test@example.com", retrievedUser.getEmail()); // Assert that the email matches
    }

    /**
     * Tests updating user information in the database.
     * It should call the corresponding DAO method.
     */
    @Test
    public void testUpdateUser() {
        UsersEntity user = new UsersEntity();
        user.setUserId(1L);
        user.setUserName("UpdatedUser");
        user.setEmail("updated@example.com");
        user.setPassword("newPassword123");

        userManagementRepository.updateUser(user); // Call the method under test

        verify(usersDao, times(1)).updateUser(user); // Verify that the DAO method was called once
    }

    /**
     * Tests deleting a user by user ID.
     * It should call the corresponding DAO method.
     */
    @Test
    public void testDeleteUser() {
        Long userId = 1L; // ID of the user to delete

        userManagementRepository.deleteUser(userId); // Call the method under test

        verify(usersDao, times(1)).deleteUser(userId); // Verify that the DAO method was called once
    }
}