package com.devtkms.apiworld.common.dto;

import lombok.Data;

@Data
public class ApiResponseDto<T> {

    private String status;

    private String message;

    private T data;

    private Integer code;
}