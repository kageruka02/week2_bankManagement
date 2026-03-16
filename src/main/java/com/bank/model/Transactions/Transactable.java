package com.bank.model.Transactions;

public interface Transactable {
    boolean processTransaction(double amount, String type);
}
