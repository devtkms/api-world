package com.devtkms.apiworld.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for the response after user registration.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDto {

    private Long userId;
}