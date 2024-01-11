package com.bosonit.Backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxPassengerLimitExceededException extends RuntimeException {
    public MaxPassengerLimitExceededException(String message) {
        super(message);
    }
}

