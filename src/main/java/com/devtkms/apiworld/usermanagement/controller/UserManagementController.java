package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {

    @Autowired
    UserManagementService userManagementService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> registerUser(
            @Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        ApiResponseDto<RegisterUserResponseDto> response = new ApiResponseDto<>();

        try {
            RegisterUserResponseDto result = userManagementService.registerUser(registerUserRequestDto);
            response.setStatus("success");
            response.setMessage("User registered successfully");
            response.setData(result);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return createErrorResponse(response, e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return createErrorResponse(response, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> createErrorResponse(
            ApiResponseDto<RegisterUserResponseDto> response, String message, HttpStatus status) {
        response.setStatus("error");
        response.setMessage(message);
        response.setData(null);
        response.setCode(status.value());
        return ResponseEntity.status(status).body(response);
    }
}