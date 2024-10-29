package com.devtkms.apiworld.filtering.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilteringRequestDto {

    private List<String> items;
    private String filterCriteria;
}
