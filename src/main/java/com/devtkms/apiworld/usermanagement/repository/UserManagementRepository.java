package com.devtkms.apiworld.usermanagement.repository;

import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing user-related database operations.
 */
@Repository
public class UserManagementRepository {

    @Autowired
    private UsersDao usersDao;

    /**
     * Inserts a new user into the database.
     *
     * @param user the UsersEntity object containing user information to be inserted
     */
    public void insertUser(UsersEntity user) {
        usersDao.insertUser(user);
    }

    /**
     * Retrieves a user from the database by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the UsersEntity object representing the user
     */
    public UsersEntity selectUser(Long userId) {
        return usersDao.selectUser(userId);
    }

    /**
     * Updates the information of an existing user in the database.
     *
     * @param user the UsersEntity object containing the updated user information
     */
    public void updateUser(UsersEntity user) {
        usersDao.updateUser(user);
    }

    /**
     * Deletes a user from the database by user ID.
     *
     * @param userId the ID of the user to delete
     */
    public void deleteUser(Long userId) {
        usersDao.deleteUser(userId);
    }
}