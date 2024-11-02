package com.devtkms.apiworld.productmanagement.service;

import com.devtkms.apiworld.productmanagement.dto.ProductRequestDto;
import com.devtkms.apiworld.productmanagement.dto.ProductResponseDto;

import java.util.List;

/**
 * Service interface for product management operations.
 */
public interface ProductManagementService {
    ProductResponseDto createProduct(ProductRequestDto request);
    ProductResponseDto getProductById(String id);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateProduct(String id, ProductRequestDto request);
    void deleteProduct(String id);
}