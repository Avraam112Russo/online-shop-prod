package com.russozaripov.securityConfig;

import com.russozaripov.authentication.AuthManager;
import com.russozaripov.bearerToken.AuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
//@EnableWebFluxSecurity
public class ConfigSecurity {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MapReactiveUserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("USER")
                .password(bCryptPasswordEncoder().encode("12345"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity, AuthManager manager, AuthenticationConverter converter){
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(manager);
        authenticationWebFilter.setServerAuthenticationConverter(converter);

        httpSecurity
                .authorizeExchange((auth) -> {
                    auth.pathMatchers("/api/test/login").permitAll()
                            .pathMatchers("/api/test/secured").authenticated();
                })
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable();
            return httpSecurity.build();
    }
}
