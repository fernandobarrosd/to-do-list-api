package com.fernando.to_do_list_api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fernando.to_do_list_api.dtos.ErrorDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class EntityExistsExceptionHandler {
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorDTO> handleEntityExists(EntityExistsException exception, HttpServletRequest request) {
        String message = exception.getMessage();
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        String path = request.getRequestURI();
        return ResponseEntity.badRequest().body(new ErrorDTO(message, path, statusCode));
    }
}