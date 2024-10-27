package com.devtkms.apiworld.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for retrieving user information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponseDto {

    private Long userId;
    private String userName;
    private String email;
}