package com.unosq.taskflow.controllers;

import com.unosq.taskflow.dtos.TaskDTO;
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
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        Task savedTask = taskService.createTask(task, userId);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
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