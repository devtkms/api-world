package com.devtkms.apiworld.configuration.service.impl;

import com.devtkms.apiworld.configuration.dto.ConfigurationDto;
import com.devtkms.apiworld.configuration.service.ConfigurationService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ConfigurationService interface using Singleton pattern.
 * This class provides access to application-wide configuration settings.
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private static ConfigurationServiceImpl instance = new ConfigurationServiceImpl();

    // Initial configuration settings (for example purposes)
    private ConfigurationDto configuration = new ConfigurationDto("defaultSetting", "defaultValue");

    private ConfigurationServiceImpl() {} // Private constructor to prevent instantiation

    public static ConfigurationServiceImpl getInstance() {
        return instance;
    }

    @Override
    public ConfigurationDto getConfiguration() {
        return configuration;
    }

    @Override
    public void updateConfiguration(ConfigurationDto newConfig) {
        this.configuration = newConfig;
    }
}