package com.devtkms.apiworld.salesAggregation.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for sales aggregation response.
 * This class holds the results of the sales aggregation operation.
 */
@Data
public class AggregateSalesResponseDto {
    private double total;    // Total sales amount
    private double average;  // Average sales amount
    private double min;      // Minimum sales amount
    private double max;      // Maximum sales amount

    // Constructor
    public AggregateSalesResponseDto(double total, double average, double min, double max) {
        this.total = total;
        this.average = average;
        this.min = min;
        this.max = max;
    }
}