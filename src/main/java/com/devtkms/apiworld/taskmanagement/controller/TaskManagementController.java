package com.devtkms.apiworld.taskmanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.taskmanagement.dto.TaskRequestDto;
import com.devtkms.apiworld.taskmanagement.dto.TaskResponseDto;
import com.devtkms.apiworld.taskmanagement.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing tasks.
 * This class handles HTTP requests related to task management operations.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskManagementController {

    @Autowired
    private TaskManagementService taskManagementService;

    /**
     * Endpoint to submit and execute a task.
     *
     * @param request the task request containing task details
     * @return ResponseEntity with the task execution result wrapped in ApiResponseDto
     */
    @PostMapping("/execute")
    public ResponseEntity<ApiResponseDto<TaskResponseDto>> executeTask(@RequestBody TaskRequestDto request) {
        TaskResponseDto response = taskManagementService.executeTask(request);
        ApiResponseDto<TaskResponseDto> apiResponse = ApiResponseDto.success(response);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}