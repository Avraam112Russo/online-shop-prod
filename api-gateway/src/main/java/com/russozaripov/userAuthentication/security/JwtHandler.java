package com.russozaripov.userAuthentication.security;

import com.russozaripov.userAuthentication.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Date;

public class JwtHandler {

    private final String secret;

    public JwtHandler(String secret) {
        this.secret = secret;
    }
    public Mono<VerificationResult> check(String token){

        return Mono.just(
                verify(token)
        ).onErrorResume(error -> Mono.error(new UnauthorizedException(error.getMessage())));
    }

    private VerificationResult verify( String token ){
        Claims claims = getClaimsToken(token);
        final Date expirationDate = claims.getExpiration();
        if (expirationDate.before(new Date())){
            throw new RuntimeException("Token expired");
        }
        return new VerificationResult(claims, token);
    }

    private Claims getClaimsToken(String token){
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
        }


    public static class VerificationResult{
        public Claims claims;
        public String token;

        public VerificationResult(Claims claims, String token) {
            this.claims = claims;
            this.token = token;
        }
    }
}
