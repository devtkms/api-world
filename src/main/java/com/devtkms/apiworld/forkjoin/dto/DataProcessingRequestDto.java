package com.devtkms.apiworld.forkjoin.dto;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for data processing requests.
 * This class holds the list of data to be processed.
 */
@Data
public class DataProcessingRequestDto {
    private List<Integer> dataList; // List of integers to be processed
}