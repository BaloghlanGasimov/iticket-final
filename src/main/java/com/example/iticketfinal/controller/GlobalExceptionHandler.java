package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.ExceptinDto;
import com.example.iticketfinal.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptinDto handler(NotFoundException e){
        log.error(e.getLogMessage());
        return new ExceptinDto(e.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptinDto handler(Exception e){
//        log.error(e.getLogMessage());
        return new ExceptinDto(e.getMessage());
    }

}
