package com.bank.model.Accounts;

import com.bank.model.Customers.Customer;
import com.bank.utils.FormatUtils;

import java.text.DecimalFormat;

public class SavingsAccount extends Account {

    private double interestRate;
    private double minimumBalance;

    public SavingsAccount(double deposit, Customer customer){
        super(deposit, customer);
        this.interestRate = 0.035f;
        this.minimumBalance = 500;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public void displayAccountDetails() {


        String accountNumber = FormatUtils.giveStringFixedLength(super.getAccountNumber(), 10);
        String customerName = FormatUtils.giveStringFixedLength(super.getCustomer().getName(), 20);
        String type = FormatUtils.giveStringFixedLength(getAccountType(), 12);
        String balance = FormatUtils.giveStringFixedLength(String.valueOf(FormatUtils.formatAmount(super.getBalance()) ) , 11);
        String status = FormatUtils.giveStringFixedLength(super.getStatus(), 10);
        String formatted = String.format("%s | %s | %s | $%s | %s", accountNumber, customerName, type,balance, status
        );
        System.out.println(formatted);
        String spaces = " ".repeat(10) ;
        String minBalance = "Min Balance: $" + FormatUtils.formatAmount( this.getMinimumBalance());
        String interestRate = "Interest Rate: " + String.format("%.1f",getInterestRate()*100) + "%" ;

        String formatted1 = String.format("%s | %s | %s", spaces, interestRate, minBalance);
        System.out.println(formatted1);

    }
    @Override
    public void withdraw(double amount){
        Double balance = calculateWithdrawal(amount);
        if (balance == null){
                    return;
        }
        super.setBalance(balance);

    }

    @Override
    public double calculateDeposit(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("amount should be greater than 0");
        }
        return super.getBalance() +amount;
    }

    @Override
    public Double calculateWithdrawal(double amount) {
        double balance = super.getBalance();
        if (amount <= 0 || amount > balance){
            System.out.println("you can only  withdraw up to "+ (balance-this.getMinimumBalance()));
            return null;
        }
        double remainingBalance =  balance - amount;

        if (remainingBalance < this.minimumBalance){
            System.out.println("you can only  withdraw up to "+ (balance-this.getMinimumBalance()));
            return null;
        }
        return remainingBalance;
    }


    public double calculateInterest(){
        double balance = super.getBalance();
        double interestEarned = Math.round(this.interestRate*balance);


        super.setBalance(balance + interestEarned);
        return interestEarned;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public boolean processTransaction(double amount, String type) {
        if (type.equalsIgnoreCase("withdraw")){
            withdraw(amount);
            return true;
        }
        if (type.equalsIgnoreCase("deposit")){
            deposit(amount);
            return true;
        }
        return false;
    }
}
