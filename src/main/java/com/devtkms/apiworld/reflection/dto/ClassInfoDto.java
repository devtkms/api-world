package com.devtkms.apiworld.reflection.dto;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Data Transfer Object for class information retrieved using reflection.
 */
@Data
public class ClassInfoDto {
    private String className;
    private String[] methods;
    private String[] fields;

    public ClassInfoDto(String className, Method[] methods, Field[] fields) {
        this.className = className;
        this.methods = new String[methods.length];
        for (int i = 0; i < methods.length; i++) {
            this.methods[i] = methods[i].toString();
        }
        this.fields = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            this.fields[i] = fields[i].toString();
        }
    }
}