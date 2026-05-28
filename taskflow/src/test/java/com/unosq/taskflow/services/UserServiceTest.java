package com.unosq.taskflow.services;

import com.unosq.taskflow.entities.User;
import com.unosq.taskflow.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock // Simulates the database repository behavior
    private UserRepository userRepository;

    @InjectMocks // Automatically injects the mocked repository into our service
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks before each test execution
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange: Setup mock data and expected behavior
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");

        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        // Act: Execute the service method
        User savedUser = userService.registerUser(user);

        // Assert: Verify results and repository interactions
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
        verify(userRepository, times(1)).save(user); // Verifies save was called exactly once
    }

    @Test
    public void testRegisterUser_ThrowsExceptionWhenEmailExists() {
        // Arrange: Simulate that the email is already in the database
        User user = new User();
        user.setEmail("existing@example.com");

        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(user));

        // Act & Assert: Verify that the proper business exception is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(user);
        });

        assertEquals("Email is already registered", exception.getMessage());
        verify(userRepository, never()).save(any(User.class)); // Verifies save was never executed
    }
}