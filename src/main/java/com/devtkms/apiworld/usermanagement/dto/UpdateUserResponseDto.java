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

    /** The ID of the updated user. */
    private Long userId;

    /** The updated name of the user. */
    private String userName;

    /** The updated email of the user. */
    private String email;

    /** The updated password of the user. */
    private String password;
}