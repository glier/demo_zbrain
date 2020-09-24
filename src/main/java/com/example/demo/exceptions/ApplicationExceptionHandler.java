package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({DuplicateException.class})
    public ResponseEntity<Map<String, Boolean>> handleRunTimeException(DuplicateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBody());
    }

}
