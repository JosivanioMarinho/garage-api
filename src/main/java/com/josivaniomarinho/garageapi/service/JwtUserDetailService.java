package com.josivaniomarinho.garageapi.service;

import com.josivaniomarinho.garageapi.GarageapiApplication;
import com.josivaniomarinho.garageapi.entity.User;
import com.josivaniomarinho.garageapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        System.out.println("O login é: " + user.getLogin() + " e a senha é: " + user.getPassword());

        if (user.getLogin().equals(login)){
            return new org.springframework.security.core.userdetails
                    .User(login, user.getPassword(), new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
    }
}
