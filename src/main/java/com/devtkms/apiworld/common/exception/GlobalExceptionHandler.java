package com.devtkms.apiworld.common.exception;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles IllegalArgumentException thrown by the application.
     *
     * @param ex the IllegalArgumentException that was thrown
     * @return a ResponseEntity containing an error response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponseDto<Void> response = ApiResponseDto.error(ex.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles all other exceptions that are not specifically handled.
     *
     * @param ex the Exception that was thrown
     * @return a ResponseEntity containing a generic error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Void>> handleGenericException(Exception ex) {
        logger.error("Unexpected error occurred", ex);
        ApiResponseDto<Void> response = ApiResponseDto.error("An unexpected error occurred", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}