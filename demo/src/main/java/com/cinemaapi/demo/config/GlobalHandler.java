package com.cinemaapi.demo.config;

import com.cinemaapi.demo.exception.ExceptionResponse;
import com.cinemaapi.demo.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerNotFoundException(NotFoundException ex){
        var exception = new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.name()
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("messages", errorMessages);
        errors.put("timestamp", LocalDateTime.now());

        return errors;
    }
}