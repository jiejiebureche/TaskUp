package com.jiedev.taskup.service.impl;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.entity.Task;
import com.jiedev.taskup.domain.entity.TaskPriority;
import com.jiedev.taskup.domain.entity.TaskStatus;
import com.jiedev.taskup.repository.TaskRepository;
import com.jiedev.taskup.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        Instant now = Instant.now();
        Task task = new Task(
                null,
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN,
                request.priority(),
                now,
                now
        );

        return taskRepository.save(task);
    }
}
