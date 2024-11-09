package com.devtkms.apiworld.configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for application configuration settings.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationDto {
    private String settingName;
    private String settingValue;
}