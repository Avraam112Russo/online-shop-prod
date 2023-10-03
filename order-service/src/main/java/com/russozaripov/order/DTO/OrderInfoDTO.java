package com.russozaripov.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoDTO {
    private String email;
    private String deliveryAddress;
    private String phoneNumber;
    private Collection<OrderItemDTO> orderItemDTOCollection;
}
