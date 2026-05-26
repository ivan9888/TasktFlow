package com.unosq.taskflow.services;

import com.unosq.taskflow.entities.User;
import com.unosq.taskflow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // Spring knows that in this class is the logic
public class UserService {

    // Dependencies Injection, here is brought the repositorie to be used
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Rule 1: save a user with the previous validation
    public User registerUser(User user) {
        // validation: if the email exists, there is an error
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email is already registered");
        }
        return userRepository.save(user);
    }

    // rule 2: Get all the users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Rule 3: search a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}