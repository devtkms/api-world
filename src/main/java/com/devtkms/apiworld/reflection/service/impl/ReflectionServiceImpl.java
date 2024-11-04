package com.devtkms.apiworld.reflection.service.impl;

import com.devtkms.apiworld.reflection.dto.ClassInfoDto;
import com.devtkms.apiworld.reflection.service.ReflectionService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Implementation of the ReflectionService interface.
 */
@Service
public class ReflectionServiceImpl implements ReflectionService {

    @Override
    public ClassInfoDto getClassInfo(String className) {
        try {
            Class<?> clazz = Class.forName(className); // Load the class dynamically

            Method[] methods = clazz.getDeclaredMethods(); // Get methods
            Field[] fields = clazz.getDeclaredFields(); // Get fields

            return new ClassInfoDto(className, methods, fields);
        } catch (ClassNotFoundException e) {
            // Handle the exception if the class is not found
            e.printStackTrace();
            return null; // Or throw a custom exception
        }
    }
}