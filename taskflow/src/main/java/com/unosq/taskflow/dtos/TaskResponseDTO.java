package com.unosq.taskflow.dtos;

import lombok.Getter;
import lombok.Setter;
//Version 1.1 This was created to solve the warning about Lazy Initialization (also the TaskController was modified)
@Getter
@Setter
public class TaskResponseDTO {
    // A clean data structure without database session dependencies
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long userId;
    private String userName;
}