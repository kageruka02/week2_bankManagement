package com.bank.model.Accounts;

import com.bank.exceptions.InvalidAmountException;
import com.bank.exceptions.OverdraftExceededException;
import com.bank.model.Customers.Customer;
import com.bank.utils.FormatUtils;

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

    public CheckingAccount(String accountNumber, Customer customer, double balance, String status) {
        super(accountNumber, customer, balance, status);
        this.overdraftLimit = 1000;
        if (customer.getCustomerType().equalsIgnoreCase("premium")){
            this.monthlyFee = 0;
        }
        else{
            this.monthlyFee = 10;
        }

    }

    @Override
    public String getAccountType() {
        return "Checking";
    }

    @Override
    public void displayAccountDetails() {


        String accountNumber = FormatUtils.giveStringFixedLength(super.getAccountNumber(), 10).toUpperCase();
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
    public  void deposit(double amount)  {
        double newBalance = calculateDeposit(amount);
        super.setBalance(newBalance);
    }

    @Override
    public  void withdraw(double amount) {
       double balance =  calculateWithdrawal(amount);
       super.setBalance(balance);
    }

    @Override
    public double calculateDeposit(double amount)  {
        if (amount <= 0){
            throw new InvalidAmountException("amount should be greater than 0");
        }
        return super.getBalance() +amount;
    }

    @Override
    public double calculateWithdrawal(double amount){
        double balance = super.getBalance();
        if (amount <= 0 ){
            String message = "Error: Invalid amount. Amount must be greater than 0";
            throw new InvalidAmountException(message);
        }
        double remainingBalance = balance - amount;

        if (   remainingBalance < -this.overdraftLimit){
            double allowedWithdrawal = balance+this.overdraftLimit;
            // Build one message string
            String message = "The overdraft is $" + FormatUtils.formatAmount(this.overdraftLimit) + "\n"
                    + "Don't exceed the overdraft, you can only withdraw " + FormatUtils.formatAmount(allowedWithdrawal) + "\n"
                    + "Your balance is $" + FormatUtils.formatAmount(this.getBalance());

            // Throw exception with full message
            throw new OverdraftExceededException(message);
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
    public boolean processTransaction(double amount, String type)  {
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
