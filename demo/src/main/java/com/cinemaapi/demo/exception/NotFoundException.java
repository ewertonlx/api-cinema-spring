package com.cinemaapi.demo.exception;

// Criando uma exceção personalizada.
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
