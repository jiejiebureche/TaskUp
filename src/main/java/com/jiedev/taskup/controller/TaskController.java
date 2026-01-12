package com.jiedev.taskup.controller;

import com.jiedev.taskup.domain.CreateTaskRequest;
import com.jiedev.taskup.domain.UpdateTaskRequest;
import com.jiedev.taskup.domain.dto.CreateTaskRequestDTO;
import com.jiedev.taskup.domain.dto.TaskDTO;
import com.jiedev.taskup.domain.dto.UpdateTaskRequestDTO;
import com.jiedev.taskup.domain.entity.Task;
import com.jiedev.taskup.mapper.TaskMapper;
import com.jiedev.taskup.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody CreateTaskRequestDTO createTaskRequestDTO) {
        CreateTaskRequest createTaskRequest = taskMapper.fromDTO(createTaskRequestDTO);
        Task task = taskService.createTask(createTaskRequest);
        TaskDTO createdTaskDTO = taskMapper.toDTO(task);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> listTask() {
        List<Task> tasks = taskService.listTasks();
        List<TaskDTO> taskDTOS = tasks.stream().map(taskMapper::toDTO).toList();
        return ResponseEntity.ok(taskDTOS);
    }

    @PatchMapping (path = "/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody UpdateTaskRequestDTO updateTaskRequestDTO) {
        UpdateTaskRequest updateTaskRequest = taskMapper.fromDTO(updateTaskRequestDTO);
        Task task = taskService.updateTask(taskId, updateTaskRequest);
        TaskDTO taskDTO = taskMapper.toDTO(task);
        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task is deleted successfully.");
    }
}
