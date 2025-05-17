package com.example.spring_mvc.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super("Bad Request");
    }
}
