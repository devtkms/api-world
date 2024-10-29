package com.devtkms.apiworld.filtering.service.impl;

import com.devtkms.apiworld.filtering.dto.FilteringRequestDto;
import com.devtkms.apiworld.filtering.service.FilteringService;
import com.devtkms.apiworld.filtering.util.FilterCriteria;
import com.devtkms.apiworld.filtering.util.FilterUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FilteringServiceImpl implements FilteringService {

    @Override
    public List<String> filterItems(FilteringRequestDto filteringRequestDto) {

        List<String> items = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        FilterCriteria<String> criteria = item -> item.contains(filteringRequestDto.getFilterCriteria());

        return FilterUtil.filter(items, criteria);
    }
}
