package com.devtkms.apiworld.reflection.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.reflection.dto.ClassInfoDto;
import com.devtkms.apiworld.reflection.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for retrieving class information using reflection.
 */
@RestController
@RequestMapping("/api/reflection")
public class ReflectionController {

    @Autowired
    private ReflectionService reflectionService;

    /**
     * Retrieves class information for the specified class name.
     *
     * @param className the name of the class to retrieve information for
     * @return ResponseEntity containing the class information wrapped in ApiResponseDto
     */
    @GetMapping("/class-info/{className}")
    public ResponseEntity<ApiResponseDto<ClassInfoDto>> getClassInfo(@PathVariable String className) {
        ClassInfoDto classInfo = reflectionService.getClassInfo(className);

        // Successful response with class information
        ApiResponseDto<ClassInfoDto> response = ApiResponseDto.success(classInfo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}