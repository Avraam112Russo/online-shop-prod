package com.russozaripov.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
    private OrderInfoDTO orderInfoDTO;
    private String username;
}
