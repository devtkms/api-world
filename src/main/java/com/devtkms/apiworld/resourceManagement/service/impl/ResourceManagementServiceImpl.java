package com.devtkms.apiworld.resourceManagement.service.impl;

import com.devtkms.apiworld.resourceManagement.dto.ResourceRequestDto;
import com.devtkms.apiworld.resourceManagement.dto.ResourceResponseDto;
import com.devtkms.apiworld.resourceManagement.service.ResourceManagementService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementation of the ResourceManagementService interface.
 * This class provides the logic for managing resources using thread pools.
 */
@Service
public class ResourceManagementServiceImpl implements ResourceManagementService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5); // Thread pool for managing resources

    @Override
    public ResourceResponseDto manageResources(ResourceRequestDto request) {
        List<String> resources = request.getResources();
        List<String> processedResources = new ArrayList<>();

        // Process resources using the thread pool
        resources.forEach(resource -> {
            executorService.submit(() -> {
                // Simulate processing resource
                String processedResource = processResource(resource);
                synchronized (processedResources) { // Avoid race conditions
                    processedResources.add(processedResource);
                }
            });
        });

        // Shutdown the executor service
        executorService.shutdown();

        return new ResourceResponseDto(processedResources);
    }

    // Simulate resource processing
    private String processResource(String resource) {
        try {
            Thread.sleep(1000); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        return "Processed: " + resource; // Return processed resource
    }
}