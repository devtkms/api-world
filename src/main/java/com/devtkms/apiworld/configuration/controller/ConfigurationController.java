package com.devtkms.apiworld.configuration.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.configuration.dto.ConfigurationDto;
import com.devtkms.apiworld.configuration.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing application configuration settings.
 * Provides endpoints to get and update configuration.
 */
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    /**
     * Gets the current configuration settings.
     *
     * @return ResponseEntity containing the configuration settings wrapped in ApiResponseDto
     */
    @GetMapping
    public ResponseEntity<ApiResponseDto<ConfigurationDto>> getConfiguration() {
        ConfigurationDto config = configurationService.getConfiguration();
        ApiResponseDto<ConfigurationDto> response = ApiResponseDto.success(config);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Updates the configuration settings.
     *
     * @param newConfig the new configuration settings
     * @return ResponseEntity indicating the outcome of the update operation
     */
    @PostMapping("/update")
    public ResponseEntity<ApiResponseDto<Void>> updateConfiguration(@RequestBody ConfigurationDto newConfig) {
        configurationService.updateConfiguration(newConfig);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDto.success(null));
    }
}