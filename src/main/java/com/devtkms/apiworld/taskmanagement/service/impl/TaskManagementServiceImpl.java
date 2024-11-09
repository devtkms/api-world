package com.devtkms.apiworld.taskmanagement.service.impl;

import com.devtkms.apiworld.taskmanagement.dto.TaskRequestDto;
import com.devtkms.apiworld.taskmanagement.dto.TaskResponseDto;
import com.devtkms.apiworld.taskmanagement.service.TaskManagementService;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the TaskManagementService interface.
 * This class provides the logic for executing tasks.
 */
@Service
public class TaskManagementServiceImpl implements TaskManagementService {

    @Override
    public TaskResponseDto executeTask(TaskRequestDto request) {
        // Simulate asynchronous task execution
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            // Simulated task processing
            try {
                Thread.sleep(1000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Processed: " + request.getTaskName();
        });

        // Wait for the task to complete and get the result
        String result = task.join(); // No exception handling for InterruptedException or ExecutionException

        return new TaskResponseDto(result);
    }
}