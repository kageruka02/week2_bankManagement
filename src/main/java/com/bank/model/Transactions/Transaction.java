package com.bank.model.Transactions;

import com.bank.utils.FormatUtils;

import java.time.Instant;

public   class Transaction {

    private static int transactionCounter;

    private String transactionId;
    private String accountNumber;
    private double amount;
    private String type;
    private final double balanceAfter;
    private Instant timeStamp;



    public Transaction(String accountNumber, double amount, String type, double balanceAfter) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
        autoGenerateTimeStamp();
    }

    // this is used to retrieve the transactions from the transactions.txt
    public Transaction(String transactionId, String accountNumber, double amount, String type, double balanceAfter, Instant timeStamp) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
        this.timeStamp = timeStamp;
        transactionCounter= transactionCounter+1;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(){
        autoGenerateTransactionId();
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }


    public String getTimeStampInSystemZone() {
        return FormatUtils.changeFromInstantToStringSystemZone(this.timeStamp);
    }

    public String getTimeStampUTC(){
        return FormatUtils.changeFromInstanttoStringUTC(this.timeStamp);
    }
    public Instant getTimeStamp() {
        return timeStamp;
    }

    private void autoGenerateTransactionId(){
        String builder = "000" + (transactionCounter + 1);
        this.transactionId =  "TXN" + builder.substring(builder.length() -3);

    }

    private void autoGenerateTimeStamp(){
        this.timeStamp = Instant.now();
    }

    public void displayTransactionDetails(){

        String transactionId = FormatUtils.giveStringFixedLength(getTransactionId(), 10);
        //getTimeStamp() is in localtime zone
        String dateTime = FormatUtils.giveStringFixedLength(getTimeStampInSystemZone(), 22);
        String type = FormatUtils.giveStringFixedLength(getType(), 12);

        String amount = "";
        if (getType().equalsIgnoreCase("deposit")){
            amount = "+$" + FormatUtils.formatAmount(getAmount());
        }
        else{
            amount = "-$" + FormatUtils.formatAmount(getAmount());
        }
        amount = FormatUtils.giveStringFixedLength(amount, 15);
        String balance = FormatUtils.giveStringFixedLength(FormatUtils.formatAmount(getBalanceAfter()) , 10);
        String formater = String.format("%s | %s | %s | %s | %s ", transactionId, dateTime, type, amount, balance);
        System.out.println(formater);

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void commit(){
        autoGenerateTransactionId();
        transactionCounter++;
    }
}
