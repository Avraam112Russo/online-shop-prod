package com.russozaripov.onlineshopproduction.DTO.product_to_productDTO;

import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.entity.Product;

public class FromProductToProductDTO {
    public static ProductDTO fromProductToProductDTO(Product product){
       return ProductDTO.builder()
                .productId(product.getId())
                .skuCode(product.getSkuCode())
                .productBrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .price(product.getDetails().getPrice())
                .description(product.getDetails().getDescription())
                .build();
    }
}
