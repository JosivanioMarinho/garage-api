package com.josivaniomarinho.garageapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoudException extends Exception{

    public UserNotFoudException(Long id) {
        super("User not found wich ID " + id);
    }
}
