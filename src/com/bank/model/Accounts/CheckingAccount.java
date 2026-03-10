package com.bank.model.Accounts;

import com.bank.model.Customers.Customer;
import com.bank.utils.FormatUtils;

import java.text.DecimalFormat;

public class CheckingAccount  extends Account {

    private double overdraftLimit;
    private double monthlyFee;

    public CheckingAccount(double deposit , Customer customer){
        super(deposit, customer);
        if (customer.getCustomerType().equalsIgnoreCase("premium")){
            this.monthlyFee = 0;
        }
        else{
            this.monthlyFee = 10;
        }
        this.overdraftLimit = 1000;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }

    @Override
    public void displayAccountDetails() {


        String accountNumber = FormatUtils.giveStringFixedLength(super.getAccountNumber(), 10);
        String customerName = FormatUtils.giveStringFixedLength(super.getCustomer().getName(), 20);
        String type = FormatUtils.giveStringFixedLength(getAccountType(), 12);


        String balance = FormatUtils.giveStringFixedLength(String.valueOf(FormatUtils.formatAmount(super.getBalance())) , 11);
        String status = FormatUtils.giveStringFixedLength(super.getStatus(), 10);
        String formatted = String.format("%s | %s | %s | $%s | %s", accountNumber, customerName, type, balance, status
        );
        System.out.println(formatted);
        String spaces = " ".repeat(10) ;
        String overdraftLimit = "Overdraft Limit: $" + FormatUtils.formatAmount(this.getOverdraftLimit());
        String monthlyFee = "Monthly Fee: $" + FormatUtils.formatAmount(this.getMonthlyFee()) ;

        String formatted1 = String.format("%s | %s | $%s", spaces,overdraftLimit, monthlyFee);
        System.out.println(formatted1);
    }

    @Override
    public void withdraw(double amount){
       Double balance =  calculateWithdrawal(amount);
       if (balance == null){
           return ;
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
        double remainingBalance = balance - amount;

        if (   remainingBalance < -this.overdraftLimit){
            double allowedWithdrawal = balance+this.overdraftLimit;
            System.out.println("Your balance is $"+ FormatUtils.formatAmount(this.getBalance()));
            System.out.println("The overdraft is $"+ FormatUtils.formatAmount(this.overdraftLimit));
            System.out.println("Don't exceed the overdraft you can only withdraw "+FormatUtils.formatAmount(allowedWithdrawal));
            return null ;
        }
        return remainingBalance;

    }



    public double applyMonthlyFee(){
        double balance = super.getBalance();
        if (super.getCustomer().getCustomerType().equalsIgnoreCase("premium")){
            return balance;
        }

        double balanceAfterDeduction = balance - this.monthlyFee;
        super.setBalance(balanceAfterDeduction);
        return balanceAfterDeduction;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
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
