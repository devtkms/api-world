package com.devtkms.apiworld.salesAggregation.service.impl;

import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesRequestDto;
import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesResponseDto;
import com.devtkms.apiworld.salesAggregation.service.SalesAggregationService;
import com.devtkms.apiworld.salesAggregation.dto.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the SalesAggregationService interface.
 * This class provides the logic for aggregating sales data.
 */
@Service
public class SalesAggregationServiceImpl implements SalesAggregationService {

    /**
     * Aggregates sales data to calculate total, average, minimum, and maximum sales amounts.
     *
     * @param request the request DTO containing the list of sales transactions
     * @return AggregateSalesResponseDto containing the total, average, minimum, and maximum sales amounts
     */
    @Override
    public AggregateSalesResponseDto aggregateSales(AggregateSalesRequestDto request) {
        List<Sale> sales = request.getSales();

        double total = sales.stream()
                .mapToDouble(Sale::getAmount) // Map each Sale object to its amount
                .sum(); // Calculate total amount

        double average = sales.stream()
                .mapToDouble(Sale::getAmount) // Map each Sale object to its amount
                .average() // Calculate average amount
                .orElse(0.0); // Default to 0 if no sales

        double minSale = sales.stream()
                .mapToDouble(Sale::getAmount) // Map each Sale object to its amount
                .min() // Find the minimum amount
                .orElse(0.0); // Default to 0 if no sales

        double maxSale = sales.stream()
                .mapToDouble(Sale::getAmount) // Map each Sale object to its amount
                .max() // Find the maximum amount
                .orElse(0.0); // Default to 0 if no sales

        // Create and return the response DTO
        return new AggregateSalesResponseDto(total, average, minSale, maxSale);
    }
}