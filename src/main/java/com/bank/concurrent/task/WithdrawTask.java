package com.bank.concurrent.task;

import com.bank.concurrent.TransactionLogger;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Transactions.Transaction;

public class WithdrawTask implements Runnable {
    private final double amount;
    private final Account account;
    private final TransactionManager transactionManager;
    public WithdrawTask(double amount, Account account, TransactionManager transactionManager){
        this.amount = amount;
        this.account = account;
        this.transactionManager = transactionManager;
    }
    @Override
    public void run() {

            synchronized (account){
                try{

                    account.processTransaction(amount, "withdraw");
                    Transaction transaction = new Transaction(
                            account.getAccountNumber(), amount, "withdraw", account.getBalance()
                    );
                    transaction.commit();
                    transactionManager.addTransaction(transaction);
                    int order = TransactionLogger.counter.getAndIncrement();

                    System.out.println("#"+ order + " "+Thread.currentThread().getName()+ ":" + "Withdraw "+ amount + "$ to "+ account.getAccountNumber());
                }
                catch(Exception e){
                    System.out.println("Withdrawal failed: ");
                }
            }

    }
}
