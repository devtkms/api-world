package com.devtkms.apiworld.resourceManagement.dto;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for resource management requests.
 * This class holds the list of resources to be processed.
 */
@Data
public class ResourceRequestDto {
    private List<String> resources; // List of resources to be managed
}