package com.devtkms.apiworld.resourceManagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.resourceManagement.dto.ResourceRequestDto;
import com.devtkms.apiworld.resourceManagement.dto.ResourceResponseDto;
import com.devtkms.apiworld.resourceManagement.service.ResourceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for resource management operations.
 * This class handles HTTP requests related to resource management.
 */
@RestController
@RequestMapping("/api/resources")
public class ResourceManagementController {

    @Autowired
    private ResourceManagementService resourceManagementService;

    /**
     * Manage resources based on the provided request.
     *
     * @param request the request DTO containing the list of resources
     * @return ResponseEntity containing the processed resources wrapped in ApiResponseDto
     */
    @PostMapping("/manage")
    public ResponseEntity<ApiResponseDto<ResourceResponseDto>> manageResources(@RequestBody ResourceRequestDto request) {
        ResourceResponseDto response = resourceManagementService.manageResources(request);
        ApiResponseDto<ResourceResponseDto> apiResponse = ApiResponseDto.success(response);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}