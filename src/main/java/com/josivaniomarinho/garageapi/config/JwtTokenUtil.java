package com.josivaniomarinho.garageapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 3600000;

    private String secret = "omaewamoushindeiru";

    //Retorna o userName/login do usuário
    public String getLoginFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Retorna expiration date do token jwt
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolve){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolve.apply(claims);
    }

    //Para retornar qualquer informação do token, iremos precisar da secred key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //Verifica se o token está expirado
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Gera token para o usuário
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenetationToken(claims, userDetails.getUsername());
    }

    //Cria token e define um tempo de expiração para ele
    private String doGenetationToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //Valida o token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String login = getLoginFromToken(token);
        return (login.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
