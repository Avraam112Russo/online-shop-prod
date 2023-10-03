package com.russozaripov.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoAboutOrderDTO {
    private String email;
    private String deliveryAddress;
    private String phoneNumber;
}
