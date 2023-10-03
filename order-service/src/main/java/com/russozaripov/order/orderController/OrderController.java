package com.russozaripov.order.orderController;

import com.russozaripov.JWTParser.ServiceJWT;
import com.russozaripov.order.DTO.InfoAboutOrderDTO;
import com.russozaripov.order.DTO.RequestOrderDTO;
import com.russozaripov.order.orderRepository.OrderRepository;
import com.russozaripov.order.orderService.ServiceOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
        private final ServiceOrder serviceOrder;
        private final ServiceJWT serviceJWT;
    @GetMapping("/getOrder")
    public ResponseEntity<String> testMethod(){
        return ResponseEntity.ok(
            "work"
        );
    }
    @PostMapping("/createNewOrder")
    public ResponseEntity<String> createNewOrder(
            @RequestBody InfoAboutOrderDTO infoAboutOrderDTO,
            @RequestHeader("Authorization") String authorization
    ){
        String username = null;
        if (authorization.startsWith("Bearer ")){
           String token = authorization.substring(7);
        username = serviceJWT.getUserName(token);
        }
        return serviceOrder.createNewOrder(infoAboutOrderDTO, username);
    }

}

