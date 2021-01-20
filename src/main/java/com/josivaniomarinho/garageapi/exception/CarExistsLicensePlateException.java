package com.josivaniomarinho.garageapi.exception;

public class CarExistsLicensePlateException extends RuntimeException {

    public CarExistsLicensePlateException(String message) {
        super(message);
    }
}
