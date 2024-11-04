package com.devtkms.apiworld.parallelprocessing.service.impl;

import com.devtkms.apiworld.parallelprocessing.dto.ParallelProcessingRequestDto;
import com.devtkms.apiworld.parallelprocessing.dto.ParallelProcessingResponseDto;
import com.devtkms.apiworld.parallelprocessing.service.ParallelProcessingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Implementation of the ParallelProcessingService interface.
 * This class provides the logic for processing tasks concurrently.
 */
@Service
public class ParallelProcessingServiceImpl implements ParallelProcessingService {

    @Override
    public ParallelProcessingResponseDto processTasks(ParallelProcessingRequestDto request) {
        List<String> tasks = request.getTasks();
        List<String> results = new ArrayList<>();

        // Create a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            // Create a list of callable tasks
            List<Callable<String>> callables = new ArrayList<>();
            for (String task : tasks) {
                callables.add(() -> {
                    // Simulate some processing (e.g., task execution)
                    Thread.sleep(1000); // Simulate delay
                    return "Processed: " + task; // Return the result
                });
            }

            // Execute tasks and gather results
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future : futures) {
                try {
                    results.add(future.get()); // Get the result of each task
                } catch (ExecutionException e) {
                    results.add("Error processing task: " + e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        } finally {
            executorService.shutdown(); // Shutdown the executor
        }

        return new ParallelProcessingResponseDto(results);
    }
}