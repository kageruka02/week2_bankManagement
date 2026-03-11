package com.bank.management;

import com.bank.model.Transactions.Transaction;
import com.bank.utils.FormatUtils;

public class TransactionManager {


    private final Transaction[] transactions = new Transaction[200];
    private int transactionCount = 0;//keep track of all transactions stored in transactions

    public void addTransaction(Transaction toBeCreatedTransaction){
        if (toBeCreatedTransaction == null){
            return;
        }
        if (transactionCount < 200){
            transactions[transactionCount] = toBeCreatedTransaction;
            transactionCount++;
        }
        else{
            System.out.println("Our database is full");
        }

    }

    /**
     * search for all transactions for accountNumber and print them from the most recent to the least recent
     *
     *
     * @param accountNumber a factor to retrieve transactions from
     * @return the number of transactions with accountNumber
     */
    public int viewTransactionsByAccount(String accountNumber){

        boolean accountAvailable = false;
        int accountHasTransactions = 0;
        for(int index=transactionCount - 1; index >=0; index--){
            Transaction transaction = transactions[index];
            if (transaction.getAccountNumber().equalsIgnoreCase(accountNumber)){
                accountHasTransactions++;
                if (accountHasTransactions == 1){
                    displayTitle(); // if the first transaction is found
                }
                accountAvailable = true;
                transaction.displayTransactionDetails();
            }
        }
        if (!accountAvailable){
            System.out.println("-".repeat(50));
            System.out.println("No transactions recorded for this account.");
            System.out.println("-".repeat(50));
        }
        return accountHasTransactions;

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
