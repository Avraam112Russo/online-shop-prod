package com.russozaripov.exceptionHandler.JWTException;

public class NoValidJWTException extends RuntimeException {
    public NoValidJWTException(String message) {
        super(message);
    }
}
