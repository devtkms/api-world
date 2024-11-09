package com.devtkms.apiworld.configuration.service;

import com.devtkms.apiworld.configuration.dto.ConfigurationDto;

/**
 * Service interface for managing application configuration settings.
 */
public interface ConfigurationService {
    ConfigurationDto getConfiguration();
    void updateConfiguration(ConfigurationDto newConfig);
}