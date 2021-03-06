package com.josivaniomarinho.garageapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

    public NotFoundException(String entity, Long id) {
        super(entity + " not found with ID " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
