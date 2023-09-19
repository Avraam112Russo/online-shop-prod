package com.russozaripov.DTO.requestResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestResponseDTO<T> implements RequestResponse<T> {

    private String message;
    private T data;
    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
