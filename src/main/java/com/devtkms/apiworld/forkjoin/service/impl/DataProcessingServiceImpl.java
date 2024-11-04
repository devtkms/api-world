package com.devtkms.apiworld.forkjoin.service.impl;

import com.devtkms.apiworld.forkjoin.dto.DataProcessingRequestDto;
import com.devtkms.apiworld.forkjoin.dto.DataProcessingResponseDto;
import com.devtkms.apiworld.forkjoin.service.DataProcessingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

/**
 * Implementation of the DataProcessingService interface.
 * This class provides the logic for processing data using Fork/Join framework.
 */
@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    @Override
    public DataProcessingResponseDto processData(DataProcessingRequestDto request) {
        List<Integer> dataList = request.getDataList(); // Assume the data is a list of integers

        // Create a ForkJoinPool and invoke the task
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(new DataProcessingTask(dataList));

        return new DataProcessingResponseDto(result);
    }

    /**
     * Recursive task for processing data using Fork/Join framework.
     */
    private static class DataProcessingTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 10; // Threshold for splitting tasks
        private final List<Integer> data;

        DataProcessingTask(List<Integer> data) {
            this.data = data;
        }

        @Override
        protected Integer compute() {
            if (data.size() <= THRESHOLD) {
                // Process data directly when below threshold
                return processData(data);
            } else {
                // Split the task into two subtasks
                int mid = data.size() / 2;
                DataProcessingTask leftTask = new DataProcessingTask(data.subList(0, mid));
                DataProcessingTask rightTask = new DataProcessingTask(data.subList(mid, data.size()));

                // Fork the subtasks
                leftTask.fork();
                Integer rightResult = rightTask.compute();
                Integer leftResult = leftTask.join(); // Wait for the left task to complete

                // Combine results
                return leftResult + rightResult;
            }
        }

        // Simple data processing function (e.g., summing the integers)
        private Integer processData(List<Integer> data) {
            return data.stream().mapToInt(Integer::intValue).sum();
        }
    }
}