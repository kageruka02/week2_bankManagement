package com.bank.management;

import com.bank.model.Transactions.Transaction;
import com.bank.utils.FormatUtils;

public class TransactionManager {


    private Transaction[] transactions = new Transaction[200];
    private int transactionCount = 0;

    public void addTransaction(Transaction toBeCreatedTransaction){
        if (transactionCount < 200 && toBeCreatedTransaction != null){
            transactions[transactionCount] = toBeCreatedTransaction;
            transactionCount++;
        }
        else{
            System.out.println("our database is full");
        }
    }

    /**
     *
     * @param accountNumber
     */
    public int viewTransactionsByAccount(String accountNumber){

        boolean accountAvailabe = false;
        int numberOfTimes = 0;
        for(int index=0; index < transactionCount; index++){
            Transaction transaction = transactions[index];
            if (transaction.getAccountNumber().equals(accountNumber)){
                numberOfTimes++;
                if (numberOfTimes == 1){
                    displayTitle();
                }
                accountAvailabe = true;
                transaction.displayTransactionDetails();
            }
        }
        if (!accountAvailabe){
            System.out.println("-".repeat(50));
            System.out.println("No transactions recorded for this account.");
            System.out.println("-".repeat(50));
        }
        return numberOfTimes;

    }

    private void displayTitle(){
        System.out.println("TRANSACTION HISTORY");
        System.out.println("_".repeat(71));
        String trxIdTitle = FormatUtils.giveStringFixedLength("TXN ID", 10);
        String dateAndTimeTitle = FormatUtils.giveStringFixedLength("DATE/TIME", 22);
        String typeTitle = FormatUtils.giveStringFixedLength("TYPE", 12);
        String amountTitle = FormatUtils.giveStringFixedLength("AMOUNT", 15);
        String balanceTitle = FormatUtils.giveStringFixedLength("BALANCE", 12);
        String formater = String.format("%s | %s | %s | %s | %s ", trxIdTitle, dateAndTimeTitle, typeTitle, amountTitle, balanceTitle);
        System.out.println(formater);

    }

    public double calculateTotalDeposits(String accountNumber){

        double sum = 0;
        for(int index=0; index < transactionCount; index++){
            Transaction trans  = transactions[index];
            if (trans.getType().equalsIgnoreCase("deposit") && trans.getAccountNumber().equalsIgnoreCase(accountNumber)){
                sum+=trans.getAmount();
            }

        }
        return sum;
    }
    public double calculateTotalWithdrawals(String accountNumber){

        double sumOfwithdrawals = 0;
        for(int index=0; index < transactionCount; index++){
            Transaction trans = transactions[index];
            if (trans.getType().equalsIgnoreCase("withdraw") &&  trans.getAccountNumber().equalsIgnoreCase(accountNumber)){
                sumOfwithdrawals+=trans.getAmount();
            }

        }
        return sumOfwithdrawals;
    }

    public int getTransactionCount(){
        return transactionCount;
    }

}
