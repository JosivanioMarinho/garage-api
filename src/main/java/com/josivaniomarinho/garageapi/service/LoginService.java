package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.config.JWTLoginFilter;
import com.josivaniomarinho.garageapi.config.TokenAuthenticationService;
import com.josivaniomarinho.garageapi.entity.User;
import com.josivaniomarinho.garageapi.exception.NotFoundException;
import com.josivaniomarinho.garageapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserRepository userRepository;

    private JWTLoginFilter loginFilter;

    @Autowired
    public LoginService(UserRepository userRepository, JWTLoginFilter loginFilter) {
        this.userRepository = userRepository;
        this.loginFilter = loginFilter;
    }

    public void findUserByLogin(String login) throws NotFoundException {
        User user = userRepository.findByLogin(login);

        if (user != null){
            loginFilter.credentials(user);
        }else{
            throw new NotFoundException("Login our password not found");
        }
    }
}
