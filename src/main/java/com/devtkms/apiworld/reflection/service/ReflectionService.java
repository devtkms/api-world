package com.devtkms.apiworld.reflection.service;

import com.devtkms.apiworld.reflection.dto.ClassInfoDto;

/**
 * Service interface for retrieving class information using reflection.
 */
public interface ReflectionService {
    ClassInfoDto getClassInfo(String className);
}