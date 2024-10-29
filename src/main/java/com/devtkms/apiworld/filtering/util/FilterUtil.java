package com.devtkms.apiworld.filtering.util;

import java.util.List;
import java.util.stream.Collectors;

public class FilterUtil {
    public static <T> List<T> filter(List<T> items, FilterCriteria<T> criteria) {
        return items.stream()
                .filter(criteria::test)
                .collect(Collectors.toList());
    }
}
