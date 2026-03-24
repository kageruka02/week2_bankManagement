package com.bank.exceptions;

public class InvalidAccountException extends RuntimeException {
   private final String message;
    public InvalidAccountException(String message ){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName()+ "  " + message;
    }
}
