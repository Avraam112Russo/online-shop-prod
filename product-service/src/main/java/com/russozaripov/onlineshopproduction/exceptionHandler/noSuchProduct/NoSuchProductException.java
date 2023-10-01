package com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct;

public class NoSuchProductException extends RuntimeException{
    public NoSuchProductException(String message) {
        super(message);
    }
}
