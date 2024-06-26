package com.georgiagrig.kioskmarketplace.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity handleEntityNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
