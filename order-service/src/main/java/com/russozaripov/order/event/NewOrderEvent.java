package com.russozaripov.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderEvent {
    private String orderNumber;
    private String orderOwner;
    private String skuCode;
}
