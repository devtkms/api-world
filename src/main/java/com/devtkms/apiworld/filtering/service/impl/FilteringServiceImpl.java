package com.devtkms.apiworld.filtering.service.impl;

import com.devtkms.apiworld.filtering.dto.FilteringRequestDto;
import com.devtkms.apiworld.filtering.service.FilteringService;
import com.devtkms.apiworld.filtering.util.FilterCriteria;
import com.devtkms.apiworld.filtering.util.FilterUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the FilteringService interface.
 * This class provides the logic for filtering items based on given criteria.
 */
@Service
public class FilteringServiceImpl implements FilteringService {

    /**
     * Filters items based on the criteria specified in the FilteringRequestDto.
     *
     * @param filteringRequestDto the request DTO containing the list of items and the filter criteria
     * @return a list of filtered items that match the specified criteria
     */
    @Override
    public List<String> filterItems(FilteringRequestDto filteringRequestDto) {
        List<String> items = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        FilterCriteria<String> criteria = item -> item.contains(filteringRequestDto.getFilterCriteria());

        return FilterUtil.filter(items, criteria);
    }
}