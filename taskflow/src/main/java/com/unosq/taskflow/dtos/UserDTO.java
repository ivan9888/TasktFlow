package com.unosq.taskflow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    // We only expose name and email to the client
    private String name;
    private String email;
}