package com.bosonit.Block7.CRUD.validation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException(String s) {
        super(s);
    }
}
