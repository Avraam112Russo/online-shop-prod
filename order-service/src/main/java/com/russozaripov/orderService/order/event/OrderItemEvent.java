package com.russozaripov.orderService.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEvent {
    private String sku_Code;
    private int quantity;
    private int price;
}
