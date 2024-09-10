package com.example.iticketfinal.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NegativeMoneyException extends RuntimeException {

    private String errorMessage;
    private String logMessage;

    public NegativeMoneyException(String errorMessage, String logMessage){
        super(errorMessage);
        this.errorMessage=errorMessage;
        this.logMessage=logMessage;
    }
}
