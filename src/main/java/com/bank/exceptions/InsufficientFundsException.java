package com.bank.exceptions;

public class InsufficientFundsException extends TransactionException {

    public InsufficientFundsException(String message ){
       super(message);
    }


}
