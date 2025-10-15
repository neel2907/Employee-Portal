package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleConflict(ResourceConflictException ex) {
        return ResponseEntity.status(409).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(500).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage()
        ));
    }
}
