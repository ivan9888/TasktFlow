package com.unosq.taskflow.services;

import com.unosq.taskflow.entities.Task;
import com.unosq.taskflow.entities.User;
import com.unosq.taskflow.repositories.TaskRepository;
import com.unosq.taskflow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // The constructor ask Spring to return both repositories automatically
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Creat a task related to specific user
    public Task createTask(Task task, Long userId) {
        // validate if the user exist before assigned the task
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        task.setUser(user); // Here the task is linked with the found user
        if (task.getStatus() == null) {
            task.setStatus("PENDING"); // status by defect
        }
        return taskRepository.save(task);
    }

    // get all the tasks of specific user
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // changing the status of the task (e.g., form PENDING to COMPLETED)
    public Task updateTaskStatus(Long taskId, String newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        task.setStatus(newStatus.toUpperCase());
        return taskRepository.save(task);
    }
}