package com.bank.Service;

import com.bank.concurrent.TransactionConcurrentExecutor;
import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.ConsoleUtils;
import com.bank.utils.InputValidation;

import java.util.Scanner;

public class ConcurrentTransactionService {


   InputValidation inputValidator = new InputValidation();
   TransactionService transactionService = new TransactionService();
    public void depositConcurrent(Scanner scanner, AccountManager accountManager, TransactionManager transactionManager) {
        System.out.println("\nCONCURRENT TRANSACTIONS");
        System.out.println("_".repeat(50));


        // step 1 - get account
        Account account = transactionService.readAndSearchValidAccount(scanner, inputValidator, accountManager);
        AccountDisplaysUtils.printAccountInTransaction(account);

        System.out.println("Running concurrent transactions deposit");
        TransactionConcurrentExecutor executor = new TransactionConcurrentExecutor();
        executor.simulateDeposits(account, transactionManager);
        executor.simulateWithdrawals(account, transactionManager);
        executor.stopTheExecutor();

        System.out.println("Thread safe operations completed successfully");
        System.out.println("The new balance is"+ account.getBalance());


    }

}
