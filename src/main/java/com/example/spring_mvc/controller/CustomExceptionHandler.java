package com.example.spring_mvc.controller;

import com.example.spring_mvc.exception.BadRequestException;
import com.example.spring_mvc.exception.NoDataFoundException;
import com.example.spring_mvc.exception.ServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler{
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> badRequestException(BadRequestException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<Map<String, Object>> serverErrorException(ServerErrorException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(NoDataFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
