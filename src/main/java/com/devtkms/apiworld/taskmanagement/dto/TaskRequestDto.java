package com.devtkms.apiworld.taskmanagement.dto;

import lombok.Data;

/**
 * Data Transfer Object for task requests.
 * Holds the details of the task to be executed.
 */
@Data
public class TaskRequestDto {
    private String taskName; // Name or description of the task
}