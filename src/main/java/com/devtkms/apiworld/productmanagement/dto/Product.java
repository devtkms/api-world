package com.devtkms.apiworld.productmanagement.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for individual product.
 */
@Data
public class Product {
    private String id;          // Product ID
    private String name;        // Product name
    private double price;       // Product price
    private String description; // Product description
}