package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.exception.ExceptionDto;
import com.example.iticketfinal.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handler(NotFoundException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionDto> handler(MethodArgumentNotValidException e) {
        List<ExceptionDto> exceptionDtos = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(
                (error) ->
                        exceptionDtos.add(new ExceptionDto(error.getField() + " : " + error.getDefaultMessage()))
        );

        return exceptionDtos;
    }

    @ExceptionHandler(WrongFileNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handler(WrongFileNameException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto("Invalid file name:" + e.getErrorMessage());
    }

    @ExceptionHandler(MaxLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handler(MaxLimitExceededException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(NegativeMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handler(NegativeMoneyException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(NotCategoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handler(NotCategoryException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handler(NotEnoughBalanceException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(UserRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handler(UserRegisteredException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(WrongAuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handler(WrongAuthException e) {
        log.error(e.getLogMessage());
        return new ExceptionDto(e.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handler(Exception e) {
//        log.error(e.getLogMessage());
        e.printStackTrace();
        return new ExceptionDto(e.getMessage());
    }

}
