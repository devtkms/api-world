package com.devtkms.apiworld.usermanagement.repository;

import com.devtkms.apiworld.usermanagement.dao.UsersDao;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class UserManagementRepositoryTest {

    @InjectMocks
    private UserManagementRepository userManagementRepository;

    @Mock
    private UsersDao usersDao;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertUser() {

        UsersEntity user = new UsersEntity();
        user.setUserId(1L);
        user.setUserName("TestUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        userManagementRepository.insertUser(user);

        verify(usersDao, times(1)).insertUser(user);
    }
}