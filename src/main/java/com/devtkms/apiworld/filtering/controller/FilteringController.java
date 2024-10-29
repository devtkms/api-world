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

@RestController
@RequestMapping("/api/filter")
public class FilteringController {

    @Autowired
    FilteringService filteringService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponseDto<List<String>>> filterItems(@RequestBody FilteringRequestDto filteringRequestDto) {
        List<String> filteredItems = filteringService.filterItems(filteringRequestDto);
        ApiResponseDto<List<String>> response = ApiResponseDto.success(filteredItems);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
