package com.jiedev.taskup.domain;

import com.jiedev.taskup.domain.entity.TaskPriority;
import com.jiedev.taskup.domain.entity.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
