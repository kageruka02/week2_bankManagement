package com.bank.management;

import com.bank.model.Transactions.Transaction;
import com.bank.utils.FormatUtils;

public class TransactionManager {


    private final Transaction[] transactions = new Transaction[200];
    private int transactionCount = 0;//keep track of all transactions stored in transactions

    /**
     * adds a transaction to persistence
     *
     * @param toBeCreatedTransaction transaction created to be added to our array
     */
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
     * y@param accountNumber a factor to be considered retrieving from our array
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

    /**
     * display title before listing all accounts available
     */
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

    /**
     *
     * @param accountNumber the account number to findBy
     * @return the sum of deposits the accountNumber has made
     */
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

    /**
     *
     * @param accountNumber the accountNumber to findby
     * @return the sum of all withdrawals the account has done
     */
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

    /**
     *
     * @return all transactions we have in our array
     */
    public int getTransactionCount(){
        return transactionCount;
    }

}
