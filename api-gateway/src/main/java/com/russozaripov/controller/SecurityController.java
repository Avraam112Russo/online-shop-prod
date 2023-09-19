package com.russozaripov.controller;

import com.russozaripov.DTO.requestLogin.RequestLoginDTO;
import com.russozaripov.DTO.requestResponse.RequestResponse;
import com.russozaripov.DTO.requestResponse.RequestResponseDTO;
import com.russozaripov.jwtService.ServiceJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class SecurityController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MapReactiveUserDetailsService detailsService;
    @Autowired
    private ServiceJWT serviceJWT;
    @PostMapping("/login")
    public Mono<ResponseEntity<RequestResponse<String>>> login(@RequestBody RequestLoginDTO loginDTO){
        Mono<UserDetails> foundUser = detailsService.findByUsername(loginDTO.getUsername()).defaultIfEmpty(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        });
        return foundUser.flatMap(user -> {
            if (user.getUsername() == null){
                return Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                new RequestResponseDTO<>("User %s not found".formatted(loginDTO.getUsername()), "NOT FOUND 404")
                        )
                );
            }
            if (bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            log.info("bCryptPasswordEncoder.matches -> generate token");
            return Mono.just(
                    ResponseEntity.ok(
                            new RequestResponseDTO<>("success", serviceJWT.generateToken(user.getUsername()))
                    )
            );
            }
            return Mono.just(
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RequestResponseDTO<>("BAD REQUEST", "404")
                    )
            );
        });
    }


    @GetMapping("/secured")
    public Mono<ResponseEntity<RequestResponseDTO<String>>> securedMethod(){
        return Mono.just(
                ResponseEntity.ok(
                        new RequestResponseDTO<>("Welocome to the private club", "success.")
                )
        );
    }
}
