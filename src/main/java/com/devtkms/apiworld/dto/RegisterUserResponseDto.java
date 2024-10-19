package com.devtkms.apiworld.dto;

import lombok.Data;

@Data
public class RegisterUserResponseDto {

    private Long userId;
    private String userName;
    private String email;
}
