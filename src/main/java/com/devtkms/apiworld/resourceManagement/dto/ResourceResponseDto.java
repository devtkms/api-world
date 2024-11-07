package com.devtkms.apiworld.resourceManagement.dto;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for resource management responses.
 * This class holds the results of the resource management operation.
 */
@Data
public class ResourceResponseDto {
    private List<String> processedResources; // List of processed resources

    // Constructor
    public ResourceResponseDto(List<String> processedResources) {
        this.processedResources = processedResources;
    }
}