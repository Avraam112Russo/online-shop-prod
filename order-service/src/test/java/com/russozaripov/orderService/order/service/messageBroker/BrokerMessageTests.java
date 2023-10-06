package com.russozaripov.orderService.order.service.messageBroker;

import com.google.common.util.concurrent.ListenableFuture;
import com.russozaripov.orderService.order.DTO.OrderInfoDTO;
import com.russozaripov.orderService.order.DTO.OrderItemDTO;
import com.russozaripov.orderService.order.DTO.RequestOrderDTO;
import com.russozaripov.orderService.order.orderService.messageBroker.BrokerMessage;
import org.bouncycastle.cert.ocsp.Req;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BrokerMessageTests {

    @Mock
    private KafkaTemplate<String, RequestOrderDTO> kafkaTemplate;
    @Mock
    private Logger logger;
    @InjectMocks
    private BrokerMessage brokerMessage;
    @DisplayName("Junit test for send message operation.")
    @Test
    public void givenRequestOrderDTO_whenSendMessage_thenReturnNothing(){
         //Given
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>(List.of(new OrderItemDTO("Apple iphone 15 Pro", 1, 159990), new OrderItemDTO("Samsung Galaxy S22", 1, 79990)));
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO("russo@mail.ru", "Moscow, Tverskaya 6", "89779993473", orderItemDTOS);
        RequestOrderDTO requestOrderDTO = new RequestOrderDTO(1, orderInfoDTO, "RUSSO");
        // When
        brokerMessage.sendMessage(requestOrderDTO);
        // Then
        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(ArgumentMatchers.anyString(), ArgumentMatchers.any(RequestOrderDTO.class));
    }
}
