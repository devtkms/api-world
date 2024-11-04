package com.devtkms.apiworld.forkjoin.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.forkjoin.dto.DataProcessingRequestDto;
import com.devtkms.apiworld.forkjoin.dto.DataProcessingResponseDto;
import com.devtkms.apiworld.forkjoin.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for data processing operations.
 * This class handles HTTP requests related to data processing using Fork/Join framework.
 */
@RestController
@RequestMapping("/api/data-processing")
public class DataProcessingController {

    @Autowired
    private DataProcessingService dataProcessingService;

    /**
     * Processes a list of data using the Fork/Join framework.
     *
     * @param request the request DTO containing the list of data to be processed
     * @return ResponseEntity containing the processed data wrapped in ApiResponseDto
     */
    @PostMapping("/process")
    public ResponseEntity<ApiResponseDto<DataProcessingResponseDto>> processData(@RequestBody DataProcessingRequestDto request) {
        DataProcessingResponseDto response = dataProcessingService.processData(request);
        ApiResponseDto<DataProcessingResponseDto> apiResponse = ApiResponseDto.success(response);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}