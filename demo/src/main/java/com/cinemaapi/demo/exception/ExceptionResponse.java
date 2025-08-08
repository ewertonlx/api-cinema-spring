package com.cinemaapi.demo.exception;

public class ExceptionResponse {
    String message;
    String code;

    public ExceptionResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
