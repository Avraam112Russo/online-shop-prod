package com.russozaripov.basket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    private String username;
    private Collection<BasketItemDTO> basketItemDTO;
}
