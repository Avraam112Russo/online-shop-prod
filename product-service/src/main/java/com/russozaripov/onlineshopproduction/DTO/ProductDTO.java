package com.russozaripov.onlineshopproduction.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int productId;
    private String skuCode;
    private String productType;
    private String productBrand;
    private int price;
    private String description;
}
