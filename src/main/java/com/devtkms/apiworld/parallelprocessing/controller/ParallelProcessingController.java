package com.devtkms.apiworld.parallelprocessing.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.parallelprocessing.dto.ParallelProcessingRequestDto;
import com.devtkms.apiworld.parallelprocessing.dto.ParallelProcessingResponseDto;
import com.devtkms.apiworld.parallelprocessing.service.ParallelProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for parallel processing operations.
 * This class handles HTTP requests related to concurrent task execution.
 */
@RestController
@RequestMapping("/api/parallel")
public class ParallelProcessingController {

    @Autowired
    private ParallelProcessingService parallelProcessingService;

    /**
     * Processes tasks concurrently and returns the results.
     *
     * @param request the request DTO containing the list of tasks to be processed
     * @return ResponseEntity containing the results wrapped in ApiResponseDto
     */
    @PostMapping("/process")
    public ResponseEntity<ApiResponseDto<ParallelProcessingResponseDto>> processTasks(@RequestBody ParallelProcessingRequestDto request) {
        ParallelProcessingResponseDto response = parallelProcessingService.processTasks(request);
        ApiResponseDto<ParallelProcessingResponseDto> apiResponse = ApiResponseDto.success(response);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}