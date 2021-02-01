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

    //Returns the userName / login of the user
    public String getLoginFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Returns expiration date of the jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolve){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolve.apply(claims);
    }

    //To return any token information, we will need the secred key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //Checks whether the token is expired
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Generates token for the user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenetationToken(claims, userDetails.getUsername());
    }

    //Create token and set an expiration time for it
    private String doGenetationToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //Validates the token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String login = getLoginFromToken(token);
        return (login.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
