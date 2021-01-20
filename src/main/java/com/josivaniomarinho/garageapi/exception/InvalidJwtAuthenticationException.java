package com.josivaniomarinho.garageapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends RuntimeException{

    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
