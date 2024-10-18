package com.devtkms.apiworld.dao;

import com.devtkms.apiworld.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersDao {

    void insertUser(UsersEntity user);

    UsersEntity selectUserById(@Param("userId") Long userId);
}