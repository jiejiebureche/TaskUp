package com.jiedev.taskup.service.impl;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.UpdateTaskRequest;
import com.jiedev.taskup.domain.entity.Task;
import com.jiedev.taskup.domain.entity.TaskPriority;
import com.jiedev.taskup.domain.entity.TaskStatus;
import com.jiedev.taskup.exception.TaskNotFoundException;
import com.jiedev.taskup.repository.TaskRepository;
import com.jiedev.taskup.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "created"));
    }

    @Override
    public Task updateTask(UUID taskId, UpdateTaskRequest request) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        if(request.title() != null) {
            task.setTitle(request.title());
        }
        if(request.description() != null) {
            task.setDescription(request.description());
        }
        if(request.dueDate() != null) {
            task.setDueDate(request.dueDate());
        }
        if(request.priority() != null) {
            task.setPriority(request.priority());
        }
        task.setStatus(request.status());
        task.setUpdated(Instant.now());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        taskRepository.deleteById(taskId);
    }
}
