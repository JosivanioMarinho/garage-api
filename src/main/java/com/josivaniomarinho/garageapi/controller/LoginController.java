package com.josivaniomarinho.garageapi.controller;

import com.josivaniomarinho.garageapi.dto.request.AccountCredentialsDTO;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
import com.josivaniomarinho.garageapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public void login(@RequestBody AccountCredentialsDTO credentialsDTO) throws NotFoundException {
        loginService.findUserByLogin(credentialsDTO.getLogin());
    }
}
