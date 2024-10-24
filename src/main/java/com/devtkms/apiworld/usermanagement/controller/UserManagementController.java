package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

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
            logger.error("Unexpected error occurred", e);
            return createErrorResponse(response, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<GetUserResponseDto>> getUserById(@PathVariable Long userId) {
        ApiResponseDto<GetUserResponseDto> response = new ApiResponseDto<>();

        try {
            GetUserResponseDto result = userManagementService.getUser(userId);
            if (result != null) {
                response.setStatus("success");
                response.setMessage("User found successfully");
                response.setData(result);
                return ResponseEntity.ok(response);
            } else {
                return createErrorResponse2(response, "User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return createErrorResponse2(response, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<UpdateUserResponseDto>> updateUser(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        ApiResponseDto<UpdateUserResponseDto> response = new ApiResponseDto<>();

        try {
            UpdateUserResponseDto result = userManagementService.updateUser(updateUserRequestDto);
            response.setStatus("success");
            response.setMessage("User updated successfully");
            response.setData(result);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return createErrorResponse3(response, e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return createErrorResponse3(response, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable Long userId) {
        ApiResponseDto<Void> response = new ApiResponseDto<>();

        try {
            userManagementService.deleteUser(userId);
            response.setStatus("success");
            response.setMessage("User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return createErrorResponse4(response, e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return createErrorResponse4(response, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
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

    private ResponseEntity<ApiResponseDto<GetUserResponseDto>> createErrorResponse2(
            ApiResponseDto<GetUserResponseDto> response, String message, HttpStatus status) {
        response.setStatus("error");
        response.setMessage(message);
        response.setData(null);
        response.setCode(status.value());
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ApiResponseDto<UpdateUserResponseDto>> createErrorResponse3(
            ApiResponseDto<UpdateUserResponseDto> response, String message, HttpStatus status) {
        response.setStatus("error");
        response.setMessage(message);
        response.setData(null);
        response.setCode(status.value());
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ApiResponseDto<Void>> createErrorResponse4(
            ApiResponseDto<Void> response, String message, HttpStatus status) {
        response.setStatus("error");
        response.setMessage(message);
        response.setData(null);
        response.setCode(status.value());
        return ResponseEntity.status(status).body(response);
    }

}