package com.russozaripov.orderService.exceptionHandler.OrderException;

public class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }
}
