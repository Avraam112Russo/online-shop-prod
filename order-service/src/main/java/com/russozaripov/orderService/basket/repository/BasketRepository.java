package com.russozaripov.orderService.basket.repository;

import com.russozaripov.orderService.basket.model.Basket;
import com.russozaripov.orderService.basket.model.ProductInBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
        Optional<Basket> findBasketByUsername(String username);
}
