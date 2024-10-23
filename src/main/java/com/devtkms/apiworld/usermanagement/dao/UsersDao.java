package com.devtkms.apiworld.usermanagement.dao;

import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersDao {

    void insertUser(UsersEntity user);

    UsersEntity selectUserById(@Param("userId") Long userId);
}