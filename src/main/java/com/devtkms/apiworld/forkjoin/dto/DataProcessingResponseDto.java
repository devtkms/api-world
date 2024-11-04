package com.devtkms.apiworld.forkjoin.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for data processing response.
 * This class holds the results of the data processing operation.
 */
@Data
public class DataProcessingResponseDto {
    private Integer result; // Result of the data processing

    // Constructor
    public DataProcessingResponseDto(Integer result) {
        this.result = result;
    }
}