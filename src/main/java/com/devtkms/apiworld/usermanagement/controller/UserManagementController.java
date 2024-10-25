package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.service.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {

    @Autowired
    UserManagementService userManagementService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> registerUser(
            @Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        RegisterUserResponseDto result = userManagementService.registerUser(registerUserRequestDto);

        ApiResponseDto<RegisterUserResponseDto> response = ApiResponseDto.success(result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<GetUserResponseDto>> getUserById(@PathVariable Long userId) {

        GetUserResponseDto result = userManagementService.getUser(userId);

        ApiResponseDto<GetUserResponseDto> response = ApiResponseDto.success(result);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<UpdateUserResponseDto>> updateUser(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {

        UpdateUserResponseDto result = userManagementService.updateUser(updateUserRequestDto);

        ApiResponseDto<UpdateUserResponseDto> response = ApiResponseDto.success(result);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponseDto.success(null));
    }
}