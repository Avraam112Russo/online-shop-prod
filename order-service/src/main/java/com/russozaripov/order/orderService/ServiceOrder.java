package com.russozaripov.order.orderService;

import com.russozaripov.order.DTO.OrderItemDTO;
import com.russozaripov.order.DTO.RequestOrderDTO;
import com.russozaripov.order.event.NewOrderEvent;
import com.russozaripov.order.model.OrderInfo;
import com.russozaripov.order.model.OrderItem;
import com.russozaripov.order.model.OrderModel;
import com.russozaripov.order.orderRepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceOrder {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, NewOrderEvent> kafkaTemplate;
    public ResponseEntity<String> createNewOrder(RequestOrderDTO requestOrderDTO){
        OrderInfo orderInfo = OrderInfo.builder()
                .deliveryAddress(requestOrderDTO.getOrderInfoDTO().getDeliveryAddress())
                .email(requestOrderDTO.getOrderInfoDTO().getEmail())
                .phoneNumber(requestOrderDTO.getOrderInfoDTO().getPhoneNumber())
                .build();
        for (OrderItemDTO dto : requestOrderDTO.getOrderInfoDTO().getOrderItemDTOCollection()){
        OrderItem orderItem = OrderItem.builder()
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .sku_Code(dto.getSku_Code())
                .build();
            orderInfo.addOrderItemsInOrderInfo(orderItem);
        }

        OrderModel orderModel = OrderModel.builder()
                .username(requestOrderDTO.getUsername())
                .orderInfo(orderInfo)
                .build();
        orderRepository.save(orderModel);
        log.info("Order with id: %s is saved.".formatted(orderModel.getId()));

        NewOrderEvent newOrderEvent = new NewOrderEvent(String.valueOf(orderModel.getId()), orderModel.getUsername(), "any skuCode");
                kafkaTemplate.send("new-order", newOrderEvent);
        log.info("order-service sent message -> " + newOrderEvent);



        return ResponseEntity.ok(
                "Order with id: %s is saved.".formatted(orderModel.getId()
        ));
    }
}
