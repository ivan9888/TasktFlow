package com.unosq.taskflow.controllers;

import com.unosq.taskflow.dtos.UserDTO;
import com.unosq.taskflow.entities.User;
import com.unosq.taskflow.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Base URL for all user operations
public class UserController {

    private final UserService userService;

    // Constructor injection for dependency management
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        // Mapping DTO data into a real database Entity
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        User savedUser = userService.registerUser(user);
        // Returning HTTP Status 201 (Created) along with the saved entity
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}