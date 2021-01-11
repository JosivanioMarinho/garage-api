package com.josivaniomarinho.garageapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserExistsEmailAndLoginException extends RuntimeException{

    public UserExistsEmailAndLoginException(String message){
        super(message);
    }
}
