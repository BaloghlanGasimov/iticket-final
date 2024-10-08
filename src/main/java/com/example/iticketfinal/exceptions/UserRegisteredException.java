package com.example.iticketfinal.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
public class UserRegisteredException extends RuntimeException {

    private String errorMessage;
    private String logMessage;

    public UserRegisteredException(String errorMessage, String logMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.logMessage = logMessage;
    }
}
