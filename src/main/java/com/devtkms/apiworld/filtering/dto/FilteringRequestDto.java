package com.devtkms.apiworld.filtering.dto;

import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) for filtering requests.
 * This class holds the list of items to be filtered and the filtering criteria.
 */
@Data
public class FilteringRequestDto {

    private List<String> items;        // List of items to be filtered
    private String filterCriteria;      // Filtering criteria to apply

}