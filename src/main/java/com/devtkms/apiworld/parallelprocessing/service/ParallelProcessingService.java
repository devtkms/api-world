package com.devtkms.apiworld.parallelprocessing.service;

import com.devtkms.apiworld.parallelprocessing.dto.ParallelProcessingRequestDto;
import com.devtkms.apiworld.parallelprocessing.dto.ParallelProcessingResponseDto;

/**
 * Service interface for parallel processing operations.
 * This interface defines methods for executing tasks concurrently.
 */
public interface ParallelProcessingService {
    ParallelProcessingResponseDto processTasks(ParallelProcessingRequestDto request);
}