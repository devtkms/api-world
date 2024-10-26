package com.devtkms.apiworld.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotNull
    private String userId;
    @NotBlank
    private String password;
}
