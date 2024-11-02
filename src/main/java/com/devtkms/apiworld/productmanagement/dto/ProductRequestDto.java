package com.devtkms.apiworld.productmanagement.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for product requests.
 */
@Data
public class ProductRequestDto {
    private String name;        // Product name
    private double price;       // Product price
    private String description; // Product description
}