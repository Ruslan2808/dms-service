package com.example.dmsservice.aop;

import com.example.dmsservice.exception.ApiError;
import com.example.dmsservice.exception.DocumentAlreadyExistsException;
import com.example.dmsservice.exception.DocumentNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    protected ResponseEntity<Object> handleDocumentNotFound(DocumentNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(DocumentAlreadyExistsException.class)
    protected ResponseEntity<Object> handleDocumentAlreadyExists(DocumentAlreadyExistsException ex) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
