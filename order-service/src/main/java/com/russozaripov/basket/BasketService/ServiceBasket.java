package com.russozaripov.basket.BasketService;

import com.russozaripov.basket.DTO.BasketDTO;
import com.russozaripov.basket.DTO.BasketItemDTO;
import com.russozaripov.basket.DTO.requestResponse.RequestResponseDTO;
import com.russozaripov.basket.model.Basket;
import com.russozaripov.basket.model.ProductInBasket;
import com.russozaripov.basket.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceBasket {

    private final BasketRepository basketRepository;

    public ResponseEntity<RequestResponseDTO<String>> createBasket(BasketDTO basketDTO, String username){
        Basket basket;
        Optional<Basket> basketOptional = basketRepository.findBasketByUsername(username);
        if (basketOptional.isPresent()){
            basket = basketOptional.get();
        }
        else {
         basket = Basket.builder()
                .username(username)
                .build();
        }
        ProductInBasket productInBasket;
         for (BasketItemDTO basketItemDTO : basketDTO.getBasketItemDTO()){
              productInBasket = ProductInBasket.builder()
                     .price(basketItemDTO.getPrice())
                     .quantity(basketItemDTO.getQuantity())
                     .skuCode(basketItemDTO.getSkuCode())
                     .build();
              basket.addProductToBasket(productInBasket);
         }
             basketRepository.save(basket);
            log.info("Basket %s save".formatted(username));
            return ResponseEntity.ok(
                    new RequestResponseDTO<>(
                            "Success",
                            "Basket %s save".formatted(username)
                    )
            );

    }
}
