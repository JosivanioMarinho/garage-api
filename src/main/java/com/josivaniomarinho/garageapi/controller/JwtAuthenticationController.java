package com.josivaniomarinho.garageapi.controller;

import com.josivaniomarinho.garageapi.config.JwtTokenUtil;
import com.josivaniomarinho.garageapi.dto.request.CredentialsDTO;
import com.josivaniomarinho.garageapi.exception.InvalidJwtAuthenticationException;
import com.josivaniomarinho.garageapi.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/signin")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailService userDetailService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid CredentialsDTO credentialsDTO) throws Exception {
        authenticate(credentialsDTO.getLogin(), credentialsDTO.getPassword());

        final UserDetails userDetails = userDetailService
                .loadUserByUsername(credentialsDTO.getLogin());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    private void authenticate(String login, String password) throws InvalidJwtAuthenticationException {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (Exception e){
            throw new InvalidJwtAuthenticationException("Invalid login or password");
        }
    }
}
