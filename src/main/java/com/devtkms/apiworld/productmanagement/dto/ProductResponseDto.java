package com.devtkms.apiworld.productmanagement.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for product responses.
 */
@Data
public class ProductResponseDto {
    private String id;          // Product ID
    private String name;        // Product name
    private double price;       // Product price
    private String description; // Product description

    public ProductResponseDto(String id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}