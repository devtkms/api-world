package com.devtkms.apiworld.taskmanagement.dto;

import lombok.Data;

/**
 * Data Transfer Object for task responses.
 * Holds the result of the executed task.
 */
@Data
public class TaskResponseDto {
    private String result; // Result or status message of the task execution

    // Constructor
    public TaskResponseDto(String result) {
        this.result = result;
    }
}