package com.unosq.taskflow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private String title;
    private String description;
    private String status; // PENDING, IN_PROGRESS, COMPLETED
}