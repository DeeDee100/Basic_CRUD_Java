package com.tutorial.crud.controllers.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException exception){
        return new ResponseEntity<> (exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(NoSuchElementException exception){
        return new ResponseEntity<> (exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
