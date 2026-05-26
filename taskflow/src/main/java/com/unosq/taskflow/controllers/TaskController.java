package com.unosq.taskflow.controllers;

import com.unosq.taskflow.dtos.TaskDTO;
import com.unosq.taskflow.dtos.TaskResponseDTO;
import com.unosq.taskflow.entities.Task;
import com.unosq.taskflow.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks") // Base URL for all task operations
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Creating a task and linking it to a specific user via URL variable
    @PostMapping("/user/{userId}")
    public ResponseEntity<TaskResponseDTO> createTask(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
        /* /*Version 1.0, when using this there was a warning: Lazy Initialization this happened when trying to get the data form database when using OneToMany.
        //the probelm is not when the data is saved,but when Spring Boot tries to transform the response into JSON format to display it to you in Postman.
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        Task savedTask = taskService.createTask(task, userId);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
         */

        //Version 1.1 Endpoint of creation updated to transform the entity of the new DTO (responseDTO) before be sent by internet.
        // Map incoming DTO to Entity
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        // Process business logic
        Task savedTask = taskService.createTask(task, userId);

        // Map saved Entity to clean Response DTO to prevent LazyInitializationException
        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setId(savedTask.getId());
        responseDTO.setTitle(savedTask.getTitle());
        responseDTO.setDescription(savedTask.getDescription());
        responseDTO.setStatus(savedTask.getStatus());
        responseDTO.setUserId(savedTask.getUser().getId());
        responseDTO.setUserName(savedTask.getUser().getName());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Retrieving all tasks assigned to a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Updating only the status of an existing task (e.g., /api/tasks/1?status=completed)
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        Task updatedTask = taskService.updateTaskStatus(taskId, status);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
}