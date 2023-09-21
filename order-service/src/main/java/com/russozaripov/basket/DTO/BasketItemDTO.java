package com.russozaripov.basket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDTO {
    private String skuCode;
    private int price;
    private int quantity;
}
