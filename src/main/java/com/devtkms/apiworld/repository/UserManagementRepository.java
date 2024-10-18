package com.devtkms.apiworld.repository;

import com.devtkms.apiworld.entity.UsersEntity;
import com.devtkms.apiworld.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserManagementRepository {

    @Autowired
    private UsersDao usersDao;

    public void saveUser(UsersEntity user) {

        usersDao.insertUser(user);
    }

    public UsersEntity getUserById(Long userId) {
        return usersDao.selectUserById(userId);
    }
}