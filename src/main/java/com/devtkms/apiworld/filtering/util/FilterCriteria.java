package com.devtkms.apiworld.filtering.util;

@FunctionalInterface
public interface FilterCriteria<T> {
    boolean test(T item);
}
