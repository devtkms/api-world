package com.devtkms.apiworld.productmanagement.service.impl;

import com.devtkms.apiworld.productmanagement.dto.Product;
import com.devtkms.apiworld.productmanagement.dto.ProductRequestDto;
import com.devtkms.apiworld.productmanagement.dto.ProductResponseDto;
import com.devtkms.apiworld.productmanagement.service.ProductManagementService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the ProductManagementService interface.
 */
@Service
public class ProductManagementServiceImpl implements ProductManagementService {
    private final Map<String, Product> productDatabase = new HashMap<>();
    private int idCounter = 1;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto request) {
        String id = String.valueOf(idCounter++);
        Product product = new Product();
        product.setId(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        productDatabase.put(id, product);
        return new ProductResponseDto(id, product.getName(), product.getPrice(), product.getDescription());
    }

    @Override
    public ProductResponseDto getProductById(String id) {
        Product product = productDatabase.get(id);
        if (product != null) {
            return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getDescription());
        }
        return null; // Handle not found scenario as per your design
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> responseList = new ArrayList<>();
        for (Product product : productDatabase.values()) {
            responseList.add(new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getDescription()));
        }
        return responseList;
    }

    @Override
    public ProductResponseDto updateProduct(String id, ProductRequestDto request) {
        Product product = productDatabase.get(id);
        if (product != null) {
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setDescription(request.getDescription());
            return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getDescription());
        }
        return null; // Handle not found scenario
    }

    @Override
    public void deleteProduct(String id) {
        productDatabase.remove(id);
    }
}