package com.example.spring_mvc.exception;

public class ServerErrorException extends RuntimeException{
    public ServerErrorException(String message) {
        super(message);
    }
}
