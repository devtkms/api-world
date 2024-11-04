package com.devtkms.apiworld.salesAggregation.service.impl;

import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesRequestDto;
import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesResponseDto;
import com.devtkms.apiworld.salesAggregation.service.SalesAggregationService;
import com.devtkms.apiworld.salesAggregation.dto.Sale;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the SalesAggregationService interface.
 * This class provides the logic for aggregating sales data.
 */
@Service
public class SalesAggregationServiceImpl implements SalesAggregationService {

    /**
     * Aggregates sales data to calculate total, average, minimum, maximum sales amounts,
     * and sales grouped by product name.
     *
     * @param request the request DTO containing the list of sales transactions
     * @return AggregateSalesResponseDto containing the aggregated results
     */
    @Override
    public AggregateSalesResponseDto aggregateSales(AggregateSalesRequestDto request) {
        List<Sale> sales = request.getSales();

        // Calculate total sales amount
        double total = sales.stream()
                .mapToDouble(Sale::getAmount)
                .sum();

        // Calculate average sales amount
        double average = sales.stream()
                .mapToDouble(Sale::getAmount)
                .average()
                .orElse(0.0);

        // Calculate minimum sales amount
        double minSale = sales.stream()
                .mapToDouble(Sale::getAmount)
                .min()
                .orElse(0.0);

        // Calculate maximum sales amount
        double maxSale = sales.stream()
                .mapToDouble(Sale::getAmount)
                .max()
                .orElse(0.0);

        // Group sales by product name and calculate total for each product
        Map<String, Double> totalByProduct = sales.stream()
                .collect(Collectors.groupingBy(Sale::getProductName,
                        Collectors.summingDouble(Sale::getAmount)));

        // Create and return the response DTO
        return new AggregateSalesResponseDto(total, average, minSale, maxSale, totalByProduct);
    }
}