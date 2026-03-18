package com.bank.exceptions;

public class OverdraftExceededException extends TransactionException {
    public OverdraftExceededException(String message ){
        super(message);
    }

}
