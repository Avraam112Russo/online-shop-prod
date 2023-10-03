package com.russozaripov.basket.controller;

import com.russozaripov.JWTParser.ServiceJWT;
import com.russozaripov.basket.BasketService.ServiceBasket;
import com.russozaripov.basket.DTO.BasketDTO;
import com.russozaripov.basket.DTO.requestResponse.RequestResponseDTO;
import com.russozaripov.exceptionHandler.JWTException.NoValidJWTException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class BasketController {

    private final ServiceBasket serviceBasket;
    private final ServiceJWT serviceJWT;

    @GetMapping("/basket")
    public ResponseEntity<RequestResponseDTO<String>> test(){
        return ResponseEntity.ok(
                new RequestResponseDTO<>(
                        "data", "work"
                )
        );
    }

    @PostMapping("/createBasket")
    public ResponseEntity<?> createBasket(
            @RequestHeader("Authorization") String authorization,
            @RequestBody BasketDTO basketDTO
    ){
        String username;
        if (authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            username = serviceJWT.getUserName(token);
        return serviceBasket.createBasket(basketDTO, username);
        }
        throw new NoValidJWTException("Имя пользователя в токене не найдено");
    }

}
