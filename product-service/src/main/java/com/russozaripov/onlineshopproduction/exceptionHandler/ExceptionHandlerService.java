//package com.russozaripov.onlineshopproduction.exceptionHandler;
//
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionHandlerService {
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException exception){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
//    }
//
//}
