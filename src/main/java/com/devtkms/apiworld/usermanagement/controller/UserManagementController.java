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
            response.setCode(null);
            return ResponseEntity.ok(response);

            // 400 Bad Request
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setMessage(e.getMessage());
            response.setData(null);
            response.setCode(400);
            return ResponseEntity.badRequest().body(response);

            // 500 Internal Server Error
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage("An unexpected error occurred");
            response.setData(null);
            response.setCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}