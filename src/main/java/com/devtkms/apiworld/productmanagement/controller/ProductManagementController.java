package com.devtkms.apiworld.productmanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.productmanagement.dto.ProductRequestDto;
import com.devtkms.apiworld.productmanagement.dto.ProductResponseDto;
import com.devtkms.apiworld.productmanagement.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for product management operations.
 */
@RestController
@RequestMapping("/api/products")
public class ProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    /**
     * Create a new product.
     *
     * @param request the product request DTO containing product information
     * @return ResponseEntity containing the created product wrapped in ApiResponseDto
     */
    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> createProduct(@RequestBody ProductRequestDto request) {
        ProductResponseDto response = productManagementService.createProduct(request);
        ApiResponseDto<ProductResponseDto> apiResponse = ApiResponseDto.success(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * Retrieve a product by its ID.
     *
     * @param id the product ID
     * @return ResponseEntity containing the product wrapped in ApiResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProductById(@PathVariable String id) {
        ProductResponseDto response = productManagementService.getProductById(id);
        return ResponseEntity.ok(ApiResponseDto.success(response));
    }

    /**
     * Retrieve all products.
     *
     * @return ResponseEntity containing the list of products wrapped in ApiResponseDto
     */
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getAllProducts() {
        List<ProductResponseDto> responseList = productManagementService.getAllProducts();
        return ResponseEntity.ok(ApiResponseDto.success(responseList));
    }

    /**
     * Update a product by its ID.
     *
     * @param id      the product ID
     * @param request the product request DTO containing updated product information
     * @return ResponseEntity containing the updated product wrapped in ApiResponseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> updateProduct(@PathVariable String id, @RequestBody ProductRequestDto request) {
        ProductResponseDto response = productManagementService.updateProduct(id, request);
        return ResponseEntity.ok(ApiResponseDto.success(response));
    }

    /**
     * Delete a product by its ID.
     *
     * @param id the product ID
     * @return ResponseEntity indicating the outcome of the delete operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteProduct(@PathVariable String id) {
        productManagementService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponseDto.success(null));
    }
}