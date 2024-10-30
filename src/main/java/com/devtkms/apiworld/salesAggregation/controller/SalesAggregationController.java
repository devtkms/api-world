package com.devtkms.apiworld.salesAggregation.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesRequestDto;
import com.devtkms.apiworld.salesAggregation.dto.AggregateSalesResponseDto;
import com.devtkms.apiworld.salesAggregation.service.SalesAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for sales aggregation operations.
 * This class handles HTTP requests related to sales data aggregation.
 */
@RestController
@RequestMapping("/api/sales")
public class SalesAggregationController {

    @Autowired
    private SalesAggregationService salesAggregationService;

    /**
     * Aggregates sales data to calculate total and average sales amount.
     *
     * @param request the request DTO containing the list of sales transactions
     * @return ResponseEntity containing the total and average sales amount wrapped in ApiResponseDto
     */
    @PostMapping("/aggregate")
    public ResponseEntity<ApiResponseDto<AggregateSalesResponseDto>> aggregateSales(@RequestBody AggregateSalesRequestDto request) {
        AggregateSalesResponseDto response = salesAggregationService.aggregateSales(request);
        ApiResponseDto<AggregateSalesResponseDto> apiResponse = ApiResponseDto.success(response);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}