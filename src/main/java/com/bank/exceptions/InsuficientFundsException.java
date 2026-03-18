package com.bank.exceptions;

public class InsuficientFundsException extends TransactionException {

    public InsuficientFundsException(String message ){
       super(message);
    }


}
