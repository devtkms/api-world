package com.devtkms.apiworld.salesAggregation.service;

import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesRequestDto;
import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesResponseDto;

/**
 * Service interface for sales aggregation operations.
 * This interface defines methods for aggregating sales data.
 */
public interface SalesAggregationService {
    AggregateSalesResponseDto aggregateSales(AggregateSalesRequestDto request);
}