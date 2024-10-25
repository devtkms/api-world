package com.devtkms.apiworld.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object for user update request.
 */
@Data
public class UpdateUserRequestDto {

    /** The ID of the user to be updated. Must not be blank. */
    @NotBlank
    private Long userId;

    /** The name of the user. Must not be blank. */
    @NotBlank
    private String userName;

    /** The email of the user. Must not be blank and must be a valid email format. */
    @NotBlank
    @Email
    private String email;

    /** The new password of the user. Must not be blank. */
    @NotBlank
    private String password;
}