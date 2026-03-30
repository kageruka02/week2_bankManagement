package com.bank.exceptions;
/*
*
* it has 3 inheritors, insufficientFundsException,
*                       InvalidAmountException,
*                       OverdraftExceededException
 */
public class TransactionException extends RuntimeException {
    private final String message;
    public TransactionException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName()+ "  " + message;
    }
}
