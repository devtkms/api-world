package com.devtkms.apiworld.resourceManagement.service;

import com.devtkms.apiworld.resourceManagement.dto.ResourceRequestDto;
import com.devtkms.apiworld.resourceManagement.dto.ResourceResponseDto;

/**
 * Service interface for managing resources.
 * This interface defines methods for processing resources.
 */
public interface ResourceManagementService {
    ResourceResponseDto manageResources(ResourceRequestDto request);
}