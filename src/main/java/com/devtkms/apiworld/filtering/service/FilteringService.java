package com.devtkms.apiworld.filtering.service;

import com.devtkms.apiworld.filtering.dto.FilteringRequestDto;

import java.util.List;

public interface FilteringService {
    List<String> filterItems(FilteringRequestDto filteringRequestDto);
}
