package com.jiedev.taskup.domain.dto;

import com.jiedev.taskup.domain.entity.TaskPriority;
import com.jiedev.taskup.domain.entity.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
