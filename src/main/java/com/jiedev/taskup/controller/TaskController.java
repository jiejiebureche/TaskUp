package com.jiedev.taskup.controller;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.dto.CreateTaskRequestDTO;
import com.jiedev.taskup.domain.dto.TaskDTO;
import com.jiedev.taskup.domain.entity.Task;
import com.jiedev.taskup.mapper.TaskMapper;
import com.jiedev.taskup.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask (@Valid @RequestBody CreateTaskRequestDTO createTaskRequestDTO){
        CreateTaskRequest createTaskRequest = taskMapper.fromDTO(createTaskRequestDTO);
        Task task = taskService.createTask(createTaskRequest);
        TaskDTO createdTaskDTO = taskMapper.toDTO(task);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }
}
