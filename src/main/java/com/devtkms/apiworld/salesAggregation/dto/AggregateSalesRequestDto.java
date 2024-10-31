package com.devtkms.apiworld.salesAggregation.dto;

import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) for sales aggregation requests.
 * This class holds the list of sales transactions to be aggregated.
 */
@Data
public class AggregateSalesRequestDto {
    private List<Sale> sales; // List of sales transactions
}