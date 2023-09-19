package com.russozaripov.DTO.requestLogin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestLoginDTO {
    private String username;
    private String password;
}
