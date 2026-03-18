package com.bank.Service;

import com.bank.exceptions.*;
import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Transactions.Transactable;
import com.bank.model.Transactions.Transaction;
import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.FormatUtils;
import com.bank.utils.InputValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionService {


    InputValidation inputValidation = new InputValidation();

    /**
     *
     * @param scanner get inputs from the console
     * @param accountManager stores the accounts
     * @return the transaction if the user approves it or null if user does not want it
     */
   public Transaction initiateTransactionCreation(Scanner scanner, AccountManager accountManager){
       System.out.println("PROCESS TRANSACTION");
       System.out.println("__________________________________________________");

       Account account = readAndSearchValidAccount(scanner, inputValidation,accountManager);

       //print account details
       AccountDisplaysUtils.printAccountInTransaction(account);

       //  decides whether deposit or withdraw
       int transactionTypeNumber = readTransactionType(scanner);

       //read amount and create transaction
      Transaction transaction = readAndPreviewTransaction(scanner, transactionTypeNumber, account);
       displayTransactionConfirmation(transaction, account);
       
       //user confirm the transaction
       String confirmationFromUser = inputValidation.getCharFrom2Chars(scanner, "Y","N", "Confirm transaction? (Y/N) " );

       boolean transactionPerformed = performRealTransaction(confirmationFromUser, account, transaction);
       if (transactionPerformed){
           
          System.out.println();
           System.out.println('✓' + "Transaction completed successfully!");
           return transaction;
       }
       else{
           return null;
       }
   }

    /**
     *
     * read amount(cash) from user(console) and then use the transaction type to create a transactionPreview
     *
     * @param scanner takes the amount of money (either deposit or withdraw) from the console
     * @param transactionType (1-deposit, 2-withdraw)
     * @param account account which will own the transaction
     * @return transaction object if the inputs are valid
     */
   private Transaction readAndPreviewTransaction(Scanner scanner, int transactionType, Account account ){
       while(true){
           try{
               System.out.print("\nEnter amount: $ ");
               double inputCash = inputValidation.getPositiveAmount(scanner,  "Enter amount: $ ");

               //create a preview transaction
               Transaction transaction =  previewTransaction(transactionType, inputCash, account);
               if (transaction != null){
                   return transaction;
               }
           }
           catch(InsuficientFundsException | OverdraftExceededException | InvalidAmountException e){
               System.out.println(e.getMessage());
           }
           catch(TransactionException e){
               System.out.println(e.getMessage());
           }
       }
   }

    /**
     *
     * @param type the number indicating the transactionType
     * @param amount input cash to deposit or withdraw
     * @param account account to which the transaction will be applied
     * @return the transaction preview
     */
   private Transaction previewTransaction(int type, double amount, Account account) throws TransactionException{
      String transactionType = determineTransactionType(type);
      if (transactionType == null){
          return null;
      }
      //does withdraw or deposit preview
      Double balancePreview = calculateBalancePreview( transactionType,  amount,  account);
      if (balancePreview == null){
          return null;
      }
       Transaction transaction = new Transaction(account.getAccountNumber(), amount, transactionType, balancePreview);
       transaction.setTransactionId();
       return transaction;

   }

   private Double calculateBalancePreview(String transactionType, double amount, Account account ) throws TransactionException {
       if ("deposit".equalsIgnoreCase(transactionType)) {
           return account.calculateDeposit(amount);
       } else if ("withdraw".equalsIgnoreCase(transactionType)) {
           return account.calculateWithdrawal(amount);
       }
       return null;
   }
    private String determineTransactionType(int type) {
        return switch (type) {
            case 1 -> "deposit";
            case 2 -> "withdraw";
            default -> null;
        };
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

    /**
     *
     *
     * @param confirmed it is the confirmation Y or N
     * @param account the account to which the transaction will be applied to
     * @param transaction the transaction to be confirmed
     * @return if the transaction is committed (true) else false
     */
   private boolean performRealTransaction(String confirmed, Account account, Transaction transaction){

       if (confirmed.equalsIgnoreCase("y")){

           try{
               transaction.commit();
               return account.processTransaction(transaction.getAmount(), transaction.getType());
           }
           catch(Exception e){
               System.out.println(e.getMessage());
           }



       }
      return false;
   }

    /**
     * searches for transactions in transactionManager and display them
     *
     * @param transactionManager it is storing all the transactions
     * @param scanner get input from the console
     * @param accountManager storing all accounts
     */
   public void printTransactionsToCli(TransactionManager transactionManager, Scanner scanner, AccountManager accountManager){

       System.out.println("GENERATE ACCOUNT STATEMENT");
       System.out.println("_".repeat(50));
       //gets account number from the console and search for it
       Account account = readAndSearchValidAccount(scanner, inputValidation,accountManager);

       //display account details to the console
       AccountDisplaysUtils.displayAccountDuringTransactionHistory(account);

       //print all transactions of the account to the console
       int allOccurrences = transactionManager.viewTransactionsByAccount(account.getAccountNumber());
       if (allOccurrences == 0){
           return;
       }
       //
       System.out.println("Total Transactions: "+ allOccurrences);
       double totalDeposits = transactionManager.calculateTotalDeposits(account.getAccountNumber());
       System.out.println("Total Deposits: $"+FormatUtils.formatAmount(totalDeposits) );
       double totalWithdrawals =  transactionManager.calculateTotalWithdrawals(account.getAccountNumber());
       System.out.println("Total Withdrawals: $"+FormatUtils.formatAmount(totalWithdrawals) );
       double netChange = Math.abs(totalDeposits - totalWithdrawals);

       String netChangeStr;
       if (totalDeposits >= totalWithdrawals){
           netChangeStr = "+$"+ FormatUtils.formatAmount(netChange);
       }
       else {
           netChangeStr = "-$"+ netChange;
       }
       System.out.println("Net Change: "+ netChangeStr);
       System.out.println( "\n" + "\u2713" + " Statement generated successfully");


   }

    /**
     * takes transaction type from the console and then validate
     *
     * @param scanner takes 1 or 2 from the user
     * @return only if the userInput from console is valid
     */
   private int readTransactionType(Scanner scanner){
       System.out.println();
       System.out.println("Transaction type: ");
       System.out.println("1. Deposit");
       System.out.println("2. Withdrawal");

       System.out.println();

       while(true){
           try{
               System.out.print("Select type (1-2): ");
               return inputValidation.getChoice(scanner, 1,2,"Select type (1-2): ") ;
           }
           catch(InputMismatchException e){
               System.out.println(e.getMessage());
           }
       }
   }

    /**
     * take accountNumber and return the account object if the account is found
     *
     * @param scanner takes accountNumber from the user
     * @param inputValidation helps to validate the accountNumber
     * @param accountManager stores the available account
     * @return account if it exists in the accountManager and continues the loop if it doesn't exist
     */
    private  Account readAndSearchValidAccount(Scanner scanner,
                                               InputValidation inputValidation,
                                               AccountManager accountManager) {

        while (true) {
            try{
                System.out.print("\nEnter Account Number: ");
                String accountNumber = inputValidation.accountValidation(scanner, "Please input a valid account number");
                return accountManager.findAccount(accountNumber);

            }
            catch(InvalidAccountException e){
                System.out.println(e.getMessage());
            }

        }

    }
}


