package com.devtkms.apiworld.taskmanagement.service;

import com.devtkms.apiworld.taskmanagement.dto.TaskRequestDto;
import com.devtkms.apiworld.taskmanagement.dto.TaskResponseDto;

/**
 * Service interface for managing tasks.
 */
public interface TaskManagementService {
    TaskResponseDto executeTask(TaskRequestDto request);
}