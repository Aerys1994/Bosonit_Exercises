package com.bosonit.Block7.CRUD.validation.controllers;

import com.bosonit.Block7.CRUD.validation.exceptions.CustomError;
import com.bosonit.Block7.CRUD.validation.exceptions.CustomEntityNotFoundException;
import com.bosonit.Block7.CRUD.validation.exceptions.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomEntityNotFoundException.class)
    public ResponseEntity<CustomError> handleCustomEntityNotFoundException(CustomEntityNotFoundException ex) {
        CustomError customError = new CustomError(new Date(), 404, ex.getMessage());
        return ResponseEntity.status(404).body(customError);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        CustomError customError = new CustomError(new Date(), 422, ex.getMessage());
        return ResponseEntity.status(422).body(customError);
    }
}
