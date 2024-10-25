package com.devtkms.apiworld.usermanagement.dao;

import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Data Access Object (DAO) interface for user-related database operations.
 */
@Mapper
public interface UsersDao {

    /**
     * Inserts a new user into the database.
     *
     * @param user the UsersEntity object containing user information to be inserted
     */
    void insertUser(UsersEntity user);

    /**
     * Retrieves a user from the database by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the UsersEntity object representing the user
     */
    UsersEntity selectUser(Long userId);

    /**
     * Updates the information of an existing user in the database.
     *
     * @param user the UsersEntity object containing the updated user information
     */
    void updateUser(UsersEntity user);

    /**
     * Deletes a user from the database by user ID.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUser(Long userId);
}