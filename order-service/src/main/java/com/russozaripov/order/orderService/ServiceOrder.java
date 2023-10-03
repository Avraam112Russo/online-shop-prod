package com.russozaripov.order.orderService;

import com.russozaripov.basket.model.Basket;
import com.russozaripov.basket.model.ProductInBasket;
import com.russozaripov.basket.repository.BasketRepository;
import com.russozaripov.order.DTO.InfoAboutOrderDTO;
import com.russozaripov.order.DTO.OrderInfoDTO;
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
    private final BasketRepository basketRepository;
//    private final KafkaTemplate<String, RequestOrderDTO> kafkaTemplate;
    private final BrokerMessage brokerMessage;
    public ResponseEntity<String> createNewOrder(InfoAboutOrderDTO infoAboutOrderDTO, String username){

        String DELIVERY_ADDRESS = infoAboutOrderDTO.getDeliveryAddress();
        String EMAIL = infoAboutOrderDTO.getEmail();
        String PHONE_NUMBER = infoAboutOrderDTO.getPhoneNumber();

        OrderInfo orderInfo = OrderInfo.builder().deliveryAddress(DELIVERY_ADDRESS).email(EMAIL).phoneNumber(PHONE_NUMBER).build();
        Order order = Order.builder().username(username).orderInfo(orderInfo).build();

        Basket basket = basketRepository.findBasketByUsername(username).get();
        List<ProductInBasket> listOfProducts = (List<ProductInBasket>) basket.getProductsInBaskets();

        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        for (ProductInBasket productInBasket : listOfProducts){
            String SKU_CODE = productInBasket.getSkuCode();
            int QUANTITY = productInBasket.getQuantity();
            int PRICE = productInBasket.getPrice();
        OrderItem orderItem = OrderItem.builder().quantity(QUANTITY).price(PRICE).sku_Code(SKU_CODE).build();
        OrderItemDTO orderItemDTO = OrderItemDTO.builder().sku_Code(SKU_CODE).price(PRICE).quantity(QUANTITY).build();
            orderItemDTOList.add(orderItemDTO);
            orderInfo.addOrderItemsInOrderInfo(orderItem);
        }

                Order savedOrder = orderRepository.save(order);
        OrderInfoDTO orderInfoDTO = OrderInfoDTO.builder()
                .deliveryAddress(DELIVERY_ADDRESS)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .orderItemDTOCollection(orderItemDTOList)
                .build();
        RequestOrderDTO requestOrderDTO = RequestOrderDTO.builder()
                .orderID(savedOrder.getId())
                .username(username)
                .orderInfoDTO(orderInfoDTO)
                .build();

                brokerMessage.sendMessage(requestOrderDTO);
                log.info("order-service sent message -> " + requestOrderDTO);

        return ResponseEntity.ok(
                "Order with id: %s is saved.".formatted(order.getId()
        ));
    }
}
