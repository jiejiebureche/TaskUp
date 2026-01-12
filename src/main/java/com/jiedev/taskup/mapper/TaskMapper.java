package com.jiedev.taskup.mapper;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.dto.CreateTaskRequestDTO;
import com.jiedev.taskup.domain.dto.TaskDTO;
import com.jiedev.taskup.domain.entity.Task;

public interface TaskMapper {
    CreateTaskRequest fromDTO(CreateTaskRequestDTO dto);
    TaskDTO toDTO(Task task);
}
