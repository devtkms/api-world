package com.devtkms.apiworld.parallelprocessing.dto;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for parallel processing response.
 * This class holds the results of the parallel processing operation.
 */
@Data
public class ParallelProcessingResponseDto {
    private List<String> results; // List of results from the processed tasks

    public ParallelProcessingResponseDto(List<String> results) {
        this.results = results;
    }
}