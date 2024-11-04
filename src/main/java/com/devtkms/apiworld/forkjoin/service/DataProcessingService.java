package com.devtkms.apiworld.forkjoin.service;

import com.devtkms.apiworld.forkjoin.dto.DataProcessingRequestDto;
import com.devtkms.apiworld.forkjoin.dto.DataProcessingResponseDto;

/**
 * Service interface for data processing operations using Fork/Join framework.
 * This interface defines methods for processing data.
 */
public interface DataProcessingService {
    DataProcessingResponseDto processData(DataProcessingRequestDto request);
}