package com.josivaniomarinho.garageapi.controller;

import jdk.jfr.Threshold;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    @GetMapping
    public String testeCars(){
        return "Hello World!";
    }
}
