package com.devtkms.apiworld.salesAggregation.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for individual sales transactions.
 * This class holds information about a sales transaction.
 */
@Data
public class Sale {
    private String productName; // Name of the product
    private double amount;       // Sale amount
}