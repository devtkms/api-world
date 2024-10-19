package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.usermanagement.dto.RegisterUserRequestDto;
import com.devtkms.apiworld.usermanagement.dto.RegisterUserResponseDto;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<RegisterUserResponseDto> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        try {
            RegisterUserResponseDto response = userManagementService.registerUser(registerUserRequestDto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // 400 Bad Request
            return ResponseEntity.badRequest().body(new RegisterUserResponseDto(null, e.getMessage()));
        } catch (Exception e) {
            // 500 Internal Server Error
            return ResponseEntity.status(500).body(new RegisterUserResponseDto(null, "An unexpected error occurred"));
        }
    }
}