package com.russozaripov.orderService.exceptionHandler.JWTException;

public class NoValidJWTException extends RuntimeException {
    public NoValidJWTException(String message) {
        super(message);
    }
}
