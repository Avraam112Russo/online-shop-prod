package com.russozaripov.basket.controller;

import com.russozaripov.basket.BasketService.ServiceBasket;
import com.russozaripov.basket.DTO.BasketDTO;
import com.russozaripov.basket.DTO.requestResponse.RequestResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class BasketController {

    private final ServiceBasket serviceBasket;

    @GetMapping("/basket")
    public ResponseEntity<RequestResponseDTO<String>> test(){
        return ResponseEntity.ok(
                new RequestResponseDTO<>(
                        "data", "work"
                )
        );
    }

    @PostMapping("/createBasket")
    public ResponseEntity<RequestResponseDTO<String>> createBasket(@RequestBody BasketDTO basketDTO){
        return serviceBasket.createBasket(basketDTO);
    }

}
