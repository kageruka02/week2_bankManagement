package com.bank.exceptions;

public class InvalidAmountException extends TransactionException{

    public InvalidAmountException(String message ){
        super(message);
    }

}
