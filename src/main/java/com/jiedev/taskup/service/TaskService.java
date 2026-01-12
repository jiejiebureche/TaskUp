package com.jiedev.taskup.service;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.entity.Task;

public interface TaskService {
    Task createTask (CreateTaskRequest request);
}
