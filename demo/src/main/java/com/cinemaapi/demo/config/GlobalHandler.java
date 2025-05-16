package com.cinemaapi.demo.config;

import com.cinemaapi.demo.exception.ExceptionResponse;
import com.cinemaapi.demo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}
