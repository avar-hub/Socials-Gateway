package com.socials.Gateway.advice;

import com.socials.Gateway.exception.TokenMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenMissingException.class)
    public ResponseEntity<String> handleTokenMissingException(TokenMissingException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }
}
