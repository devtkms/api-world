package com.devtkms.apiworld.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String status;
    private T data;
    private String message;
    private int code;

    public static <T> ApiResponseDto<T> success(T data) {
        return new ApiResponseDto<>("success", data, "Request completed successfully", 200);
    }

    public static ApiResponseDto<Void> error(String message, int code) {
        return new ApiResponseDto<>("error", null, message, code);
    }
}