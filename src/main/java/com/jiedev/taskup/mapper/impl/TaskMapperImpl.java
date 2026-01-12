package com.jiedev.taskup.mapper.impl;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.dto.CreateTaskRequestDTO;
import com.jiedev.taskup.domain.dto.TaskDTO;
import com.jiedev.taskup.domain.entity.Task;
import com.jiedev.taskup.mapper.TaskMapper;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public CreateTaskRequest fromDTO(CreateTaskRequestDTO dto) {
        return new CreateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
