package com.bank.model.Accounts;

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
    };

    public Account(double balance, Customer customer){
        this.balance = balance;
        setAccountNumber();
        this.customer = customer;
    }

    public abstract String getAccountType();

    public abstract void displayAccountDetails();

    public void deposit(double amount){

        if (amount <= 0){
            throw new IllegalArgumentException("amount should be greater than 0");
        }
        this.balance+=amount;
    }

    public void withdraw(double amount){
        if (amount < 0 || amount > balance){
            throw new IllegalArgumentException("amount should be greater than 0");
        }
        this.balance-=amount;
    }

    public abstract  double calculateDeposit(double amount);
    public abstract  Double calculateWithdrawal(double amount);

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
