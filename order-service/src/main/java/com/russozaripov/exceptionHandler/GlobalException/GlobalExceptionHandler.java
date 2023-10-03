package com.russozaripov.exceptionHandler.GlobalException;

import com.russozaripov.exceptionHandler.JWTException.JwtTokenIncorrectData;
import com.russozaripov.exceptionHandler.JWTException.NoValidJWTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<JwtTokenIncorrectData> incorrectJWT(NoValidJWTException noValidJWTException){
        JwtTokenIncorrectData incorrectData = new JwtTokenIncorrectData();
        incorrectData.setInfo(noValidJWTException.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}
