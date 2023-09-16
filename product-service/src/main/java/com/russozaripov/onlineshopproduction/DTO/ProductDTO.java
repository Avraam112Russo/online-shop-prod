package com.russozaripov.onlineshopproduction.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int productId;
    private boolean isInStock;
    private String skuCode;
    private String title;
    private String productType;
    private String productBrand;
    private int price;
    private String description;
    private LocalTime localTime;
}
