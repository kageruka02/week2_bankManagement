package com.bank.concurrent;

import com.bank.concurrent.task.DepositTask;
import com.bank.concurrent.task.WithdrawTask;
import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionConcurrentExecutor {

    public  final ExecutorService executor = Executors.newFixedThreadPool(5);

    public  void simulateDeposits(Account account, TransactionManager transactionManager){

        for (int i=0; i < 10; i++){
            executor.submit(new DepositTask(3, account, transactionManager));
        }
    }

    public  void simulateWithdrawals(Account account, TransactionManager transactionManager){
        for (int i=0; i < 10; i++){
            executor.submit(new WithdrawTask(3, account, transactionManager));
        }
    }

    public  void stopTheExecutor(){
            try {
                executor.shutdown();
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Thread pool shutdown interrupted: " + e.getMessage());
            }

    }
}
