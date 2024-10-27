package com.devtkms.apiworld.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for the response after updating user information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDto {

    private Long userId;
    private String userName;
    private String email;
    private String password;
}