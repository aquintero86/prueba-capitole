package com.capitole.infraestructure.rest.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestResponseException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<String>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField() + " : "+error.getDefaultMessage()));
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                "Bad request please validate it");


        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResponseStatusException ex) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getStatus().getReasonPhrase());
        ErrorMessage message = new ErrorMessage(
                ex.getRawStatusCode(),
                errors,
                ex.getReason());

        return new ResponseEntity<ErrorMessage>(message, ex.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        ErrorMessage message = new ErrorMessage(404,
                errors,
                ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }




}
