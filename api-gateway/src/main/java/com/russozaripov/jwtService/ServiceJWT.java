package com.russozaripov.jwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class ServiceJWT {
    private SecretKey secretKey;
    private JwtParser jwtParser;

    public ServiceJWT() {
        this.secretKey = Keys.hmacShaKeyFor("qwertyuioasdfghjklqwertyuiopzxcvbnmasasssssssss".getBytes());
        this.jwtParser = Jwts.parserBuilder().setSigningKey(this.secretKey).build();
    }

    public String generateToken(String username){
     return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now())) // время вызова метода
                .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
                .signWith(secretKey)
                .compact();
    }
    public String getUserName(String token){
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token, UserDetails userDetails){
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        boolean unexpired = claims.getExpiration().after(Date.from(Instant.now()));
        return unexpired && claims.getSubject().equals(userDetails.getUsername());

    }
}
