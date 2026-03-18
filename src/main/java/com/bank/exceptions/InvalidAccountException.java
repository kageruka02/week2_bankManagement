package com.bank.exceptions;

public class InvalidAccountException extends Exception {
   private final String message;
    public InvalidAccountException(String message ){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName()+ "  " + message;
    }
}
