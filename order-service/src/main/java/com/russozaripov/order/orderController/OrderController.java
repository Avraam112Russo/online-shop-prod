package com.russozaripov.order.orderController;

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
    @GetMapping("/getOrder")
    public ResponseEntity<String> testMethod(){
        return ResponseEntity.ok(
            "work"
        );
    }
    @PostMapping("/createNewOrder")
    public ResponseEntity<String> createNewOrder(@RequestBody RequestOrderDTO requestOrderDTO){
        return serviceOrder.createNewOrder(requestOrderDTO);
    }

}

