package com.mak.ecommerce.userservice.controller.advice;

import com.mak.ecommerce.userservice.dtos.ExceptionDto;
import com.mak.ecommerce.userservice.exception.TokenNotFoundException;
import com.mak.ecommerce.userservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(UserNotFoundException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setStatus("Failure");
        dto.setMessage(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleTokenNotFoundException(TokenNotFoundException e){
        ExceptionDto dto = new ExceptionDto();
        dto.setStatus("Failure");
        dto.setMessage(e.getMessage());
        return  new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
