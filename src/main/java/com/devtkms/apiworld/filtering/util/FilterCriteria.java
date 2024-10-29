package com.devtkms.apiworld.filtering.util;

/**
 * Functional interface for defining filter criteria.
 * This interface is used to test whether a given item meets specific conditions.
 *
 * @param <T> the type of the item to be tested
 */
@FunctionalInterface
public interface FilterCriteria<T> {
    /**
     * Tests whether the specified item meets the criteria.
     *
     * @param item the item to be tested
     * @return true if the item meets the criteria; false otherwise
     */
    boolean test(T item);
}