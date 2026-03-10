package com.bank.Service;

import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Transactions.Transaction;
import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.FormatUtils;
import com.bank.utils.InputValidation;

import java.util.Scanner;

public class TransactionService {


    InputValidation inputValidation = new InputValidation();


   public Transaction initiateTransactionCreation(Scanner scanner, AccountManager accountManager){
       System.out.println("PROCESS TRANSACTION");
       System.out.println("__________________________________________________");

       Account account = readAndSearchValidAccount(scanner, inputValidation,accountManager);
       AccountDisplaysUtils.printAccountInTransaction(account);
       int transactionTypeNumber = readTransactionType(scanner);

      Transaction transaction = readAndPreviewTransaction(scanner, transactionTypeNumber, account);
       displayTransactionConfirmation(transaction, account);
       String confirmFromUser = inputValidation.getCharFrom2Chars(scanner, "Y","N", "Confirm transaction? (Y/N) " );

       if (performRealTransaction(confirmFromUser, account, transaction)){
          System.out.println();
           System.out.println('✓' + "Transaction completed successfully!");
           return transaction;
       }
       else{
           return null;
       }
   }

   private Transaction readAndPreviewTransaction(Scanner scanner, int type, Account account ){
       while(true){
           System.out.print("\nEnter amount: $");
           double inputCash = inputValidation.getPositiveAmount(scanner,  "Enter amount: $ ");
           Transaction transaction = previewTransaction(type, inputCash, account);
           if (transaction != null){
               return transaction;
           }
       }
   }

   private Transaction previewTransaction(int type, double amount, Account account){
      String transactionType = determineTransactionType(type);
      if (transactionType == null){
          return null;
      }
      Double balancePreview = calculateBalancePreview( transactionType,  amount,  account);
      if (balancePreview == null){
          return null;
      }
       Transaction transaction = new Transaction(account.getAccountNumber(), amount, transactionType, balancePreview);
       transaction.setTransactionId();
       return transaction;

   }

   private Double calculateBalancePreview(String transactionType, double amount, Account account ){
       if ("deposit".equalsIgnoreCase(transactionType)) {
           return account.calculateDeposit(amount);
       } else if ("withdraw".equalsIgnoreCase(transactionType)) {
           return account.calculateWithdrawal(amount);
       }
       return null;
   }
    private String determineTransactionType(int type) {
       switch(type){
           case 1: return "deposit";
           case 2: return "withdraw";
           default: return null;
       }
    }

    private void displayTransactionConfirmation(Transaction transaction, Account account){
       System.out.println();
        System.out.println("TRANSACTION CONFIRMATION");
        System.out.println("_".repeat(50));
        System.out.println("Transaction ID:" + transaction.getTransactionId());
        System.out.println("Account: "+ account.getAccountNumber());
        System.out.println("Type: "+ transaction.getType().toUpperCase());
        System.out.println("Amount: $"+ FormatUtils.formatAmount(transaction.getAmount()) );
        System.out.println("Previous Balance: $"+ FormatUtils.formatAmount(account.getBalance()) );
        System.out.println("New Balance: $"+FormatUtils.formatAmount(transaction.getBalanceAfter()) );
        System.out.println("Date/Time: "+ transaction.getTimeStamp());

        System.out.println("-".repeat(50));

        System.out.println();
        System.out.print("Confirm transaction? (Y/N) ");
    }


   private boolean performRealTransaction(String confirmed, Account account, Transaction transaction){

       if (confirmed.equalsIgnoreCase("y")){

           transaction.commit();
           return account.processTransaction(transaction.getAmount(), transaction.getType());


       }
      return false;
   }

   public void printTransactionsToCli(TransactionManager transactionManager, Scanner scanner, AccountManager accountManager){

       System.out.println("VIEW TRANSACTION HISTORY");
       System.out.println("_".repeat(50));
       Account account = readAndSearchValidAccount(scanner, inputValidation,accountManager);
       AccountDisplaysUtils.displayAccountDuringTransactionHistory(account);


       int allOccurrences = transactionManager.viewTransactionsByAccount(account.getAccountNumber());
       if (allOccurrences == 0){
           return;
       }
       System.out.println("Total Transactions: "+ allOccurrences);
       double totalDeposits = transactionManager.calculateTotalDeposits(account.getAccountNumber());
       System.out.println("Total Deposits: $"+ totalDeposits);
       double totalWithdrawals =  transactionManager.calculateTotalWithdrawals(account.getAccountNumber());
       System.out.println("Total Withdrawals: $"+ totalWithdrawals);
       double netChange = Math.abs(totalDeposits - totalWithdrawals);

       String netChangeStr;
       if (totalDeposits >= totalWithdrawals){
           netChangeStr = "+$"+ netChange;
       }
       else {
           netChangeStr = "-$"+ netChange;
       }
       System.out.println("Net Change: "+ netChangeStr);


   }

   private int readTransactionType(Scanner scanner){
       System.out.println();
       System.out.println("Transaction type: ");
       System.out.println("1. Deposit");
       System.out.println("2. Withdrawal");

       System.out.println();
       System.out.print("Select type (1-2): ");
       return inputValidation.getChoice(scanner, 1,2,"Select type (1-2): " );
   }


    private  Account readAndSearchValidAccount(Scanner scanner,
                                               InputValidation inputValidation,
                                               AccountManager accountManager) {
        Account account = null;

        while (account == null) {
            System.out.print("\nEnter Account Number: ");
            String accountNumber = inputValidation.accountValidation(scanner, "Please input a valid account number");
            account = accountManager.findAccount(accountNumber);

            if (account == null) {
                System.out.println("Account not found. Try again.");
            }
        }

        return account;
    }
}


