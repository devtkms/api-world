package com.devtkms.apiworld.parallelprocessing.dto;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for parallel processing requests.
 * This class holds the list of tasks to be executed concurrently.
 */
@Data
public class ParallelProcessingRequestDto {
    private List<String> tasks; // List of tasks to be processed
}