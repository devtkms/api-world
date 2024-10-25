package com.devtkms.apiworld.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for API responses.
 *
 * @param <T> the type of data included in the response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String status;
    private T data;
    private String message;
    private int code;

    /**
     * Creates a success response.
     *
     * @param data the data to include in the response
     * @return an ApiResponseDto representing a successful response
     */
    public static <T> ApiResponseDto<T> success(T data) {
        return new ApiResponseDto<>("success", data, "Request completed successfully", 200);
    }

    /**
     * Creates an error response.
     *
     * @param message the error message to include in the response
     * @param code the HTTP status code associated with the error
     * @return an ApiResponseDto representing an error response
     */
    public static ApiResponseDto<Void> error(String message, int code) {
        return new ApiResponseDto<>("error", null, message, code);
    }
}