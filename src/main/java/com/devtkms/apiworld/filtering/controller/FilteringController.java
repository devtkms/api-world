package com.devtkms.apiworld.filtering.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.filtering.dto.FilteringRequestDto;
import com.devtkms.apiworld.filtering.service.FilteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for filtering operations.
 * This class handles HTTP requests related to item filtering.
 */
@RestController
@RequestMapping("/api/filter")
public class FilteringController {

    @Autowired
    FilteringService filteringService;

    /**
     * Filters items based on the provided filter criteria.
     *
     * @param filteringRequestDto the request DTO containing the list of items and the filter criteria
     * @return ResponseEntity containing the filtered items wrapped in ApiResponseDto
     */
    @PostMapping("/items")
    public ResponseEntity<ApiResponseDto<List<String>>> filterItems(@RequestBody FilteringRequestDto filteringRequestDto) {
        List<String> filteredItems = filteringService.filterItems(filteringRequestDto);
        ApiResponseDto<List<String>> response = ApiResponseDto.success(filteredItems);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}