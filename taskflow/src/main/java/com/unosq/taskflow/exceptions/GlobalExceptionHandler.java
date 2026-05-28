package com.unosq.taskflow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Intercepts exceptions thrown by any Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // Catches all RuntimeExceptions
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        // Returning a clean JSON with HTTP 404 Not Found instead of a 500 server crash
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}