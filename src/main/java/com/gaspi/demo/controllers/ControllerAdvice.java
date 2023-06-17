package com.gaspi.demo.controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gaspi.demo.exceptions.ErrorRest;
import com.gaspi.demo.exceptions.RequestException;

@RestControllerAdvice
public class ControllerAdvice {
    
    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorRest> requestExceptionHandler(RequestException ex){
        ErrorRest error = ErrorRest.builder().title(ex.getTitle()).severity(ex.getSeverity()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    } 
}
