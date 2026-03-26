package com.bank.model.Accounts;

import com.bank.exceptions.InvalidAmountException;
import com.bank.exceptions.TransactionException;
import com.bank.model.Customers.Customer;
import com.bank.model.Transactions.Transactable;


public abstract class Account implements Transactable {

    private String accountNumber;
    private Customer customer;
    private double balance;
    private String status = "Active";

    static int accountCounter = 0;

    public Account(){
        setAccountNumber();
    }

    public Account(double balance, Customer customer){
        this.balance = balance;
        setAccountNumber();
        this.customer = customer;
    }

    //for retrieving accounts from the file
    public Account(String accountNumber, Customer customer, double balance, String status) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.status = status;
        accountCounter  = accountCounter +1;
    }

    public abstract String getAccountType();

    public abstract void displayAccountDetails();

    public abstract void  deposit(double amount) throws InvalidAmountException;

    public abstract void withdraw(double amount) throws TransactionException;

    public abstract  double calculateDeposit(double amount) throws InvalidAmountException;
    public abstract  double calculateWithdrawal(double amount) throws TransactionException;

    public String getAccountNumber() {
        return accountNumber;
    }

    private void setAccountNumber() {
        Account.accountCounter++;
        int n = Account.accountCounter;
        String gen = "000"+ n;
        this.accountNumber = "ACC"+ gen.substring(gen.length()-3);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
