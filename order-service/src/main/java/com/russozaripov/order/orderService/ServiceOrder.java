package com.russozaripov.order.orderService;

import com.russozaripov.order.DTO.OrderItemDTO;
import com.russozaripov.order.DTO.RequestOrderDTO;
import com.russozaripov.order.event.NewOrderEvent;
import com.russozaripov.order.event.OrderItemEvent;
import com.russozaripov.order.messageBroker.BrokerMessage;
import com.russozaripov.order.model.OrderInfo;
import com.russozaripov.order.model.OrderItem;
import com.russozaripov.order.model.Order;
import com.russozaripov.order.orderRepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceOrder {
    private final OrderRepository orderRepository;
//    private final KafkaTemplate<String, RequestOrderDTO> kafkaTemplate;
    private final BrokerMessage brokerMessage;
    public ResponseEntity<String> createNewOrder(RequestOrderDTO requestOrderDTO){
        OrderInfo orderInfo = OrderInfo.builder()
                .deliveryAddress(requestOrderDTO.getOrderInfoDTO().getDeliveryAddress())
                .email(requestOrderDTO.getOrderInfoDTO().getEmail())
                .phoneNumber(requestOrderDTO.getOrderInfoDTO().getPhoneNumber())
                .build();

        Order order = Order.builder()
                .username(requestOrderDTO.getUsername())
                .orderInfo(orderInfo)
                .build();


        for (OrderItemDTO dto : requestOrderDTO.getOrderInfoDTO().getOrderItemDTOCollection()){
        OrderItem orderItem = OrderItem.builder()
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .sku_Code(dto.getSku_Code())
                .build();
            orderInfo.addOrderItemsInOrderInfo(orderItem);

        }

                orderRepository.save(order);
                requestOrderDTO.setOrderID(order.getId());
                brokerMessage.sendMessage(requestOrderDTO);
                log.info("order-service sent message -> " + requestOrderDTO);

        return ResponseEntity.ok(
                "Order with id: %s is saved.".formatted(order.getId()
        ));
    }
}
