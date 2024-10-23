package com.devtkms.apiworld.usermanagement.repository;

import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import com.devtkms.apiworld.usermanagement.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserManagementRepository {

    @Autowired
    private UsersDao usersDao;

    public void insertUser(UsersEntity user) {

        usersDao.insertUser(user);
    }
}