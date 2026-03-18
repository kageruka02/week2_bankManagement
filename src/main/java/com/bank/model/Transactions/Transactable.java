package com.bank.model.Transactions;


import com.bank.exceptions.TransactionException;

public interface Transactable {
    boolean processTransaction(double amount, String type) throws TransactionException;
}
