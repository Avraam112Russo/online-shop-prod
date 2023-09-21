package com.russozaripov.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private String sku_Code;
    private int quantity;
    private int price;
}
