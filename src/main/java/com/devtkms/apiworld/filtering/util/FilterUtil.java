package com.devtkms.apiworld.filtering.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for filtering operations.
 * This class provides static methods to filter lists based on criteria.
 */
public class FilterUtil {
    /**
     * Filters a list of items based on the specified criteria.
     *
     * @param items   the list of items to filter
     * @param criteria the criteria used for filtering the items
     * @param <T>    the type of items in the list
     * @return a list of items that match the specified criteria
     */
    public static <T> List<T> filter(List<T> items, FilterCriteria<T> criteria) {
        return items.stream()
                .filter(criteria::test)
                .collect(Collectors.toList());
    }
}