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

    /** The ID of the user. */
    private Long userId;

    /** The name of the user. */
    private String userName;

    /** The email of the user. */
    private String email;
}