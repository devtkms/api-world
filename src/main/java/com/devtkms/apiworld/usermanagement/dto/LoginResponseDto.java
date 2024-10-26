package com.devtkms.apiworld.usermanagement.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private Long userId;
    private String userName;
    private String email;
}
