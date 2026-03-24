package com.bank.Service;


import com.bank.exceptions.PersistenceException;
import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.ConsoleUtils;
import com.bank.utils.InputValidation;

import java.io.IOException;
import java.util.Scanner;

public class MenuService {


    private final InputValidation inputValidation= new InputValidation();
    private final Scanner scanner = new Scanner(System.in);
    private final AccountService accountService = new AccountService();
    AccountManager accountManager = new AccountManager();
    TransactionManager transactionManager = new TransactionManager();
    TransactionService transactionService = new TransactionService();


    public   void starter(){

        accountService.loadAccountsFromFile(accountManager);
        //register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            try{
                FilePersistenceService.persistAllAccounts(accountManager.getAllAccounts());

            }catch(PersistenceException e){
                System.out.println("Accounts or transactions were not persisted");
            }
        }));
        while(true){

            printMenuService();
            System.out.print("Enter choice: ");
            int menuOption = inputValidation.rangeChoice(scanner, 1,5, "Enter choice: ");

            switch(menuOption){
                case 1:
                  addAccountToAccountManager(accountService, accountManager, scanner);
                  ConsoleUtils.waitForEnter(scanner);
                  break;
                case 2:
                    AccountDisplaysUtils.printAllAccountsToCLi(accountManager);
                    ConsoleUtils.waitForEnter(scanner);
                    break;

                case 3:
                   transactionManager.addTransaction(transactionService.initiateTransactionCreation(scanner, accountManager));
                   ConsoleUtils.waitForEnter(scanner);
                    break;
//                case 3:
//                    transactionService.printTransactionsToCli(transactionManager, scanner, accountManager);
//                    ConsoleUtils.waitForEnter(scanner);
//                    break;
                case 4:
                    System.out.println("Thank you for using Bank Account Management System!");
                    System.out.println("Goodbye!");
                    return;

            }
        }
    }

    private void printMenuService(){
        String text ="||   " +  "BANK ACCOUNT MANAGEMENT - MAIN MENU" + "    ||";
        String horizontalLine1 =     "_".repeat(text.length()-1);
        String verticalLine1 = "||" + " ".repeat(text.length() -4) + "||";
        String horizontalLine2 = "| " + "-".repeat(text.length()-4) + " |";


        System.out.println(horizontalLine1);
        System.out.println(horizontalLine2);
        System.out.println(verticalLine1);
        System.out.println(verticalLine1);
        System.out.println(verticalLine1);
        System.out.println(text);
        System.out.println(verticalLine1);
        System.out.println(horizontalLine2);
        System.out.println(horizontalLine1);

        System.out.println("1. Manage accounts");
//        System.out.println("2. View Accounts");
        System.out.println("2. Process Transaction");
        System.out.println("3. Generate Account Statements");
        System.out.println("4. Exit");
    }

    private void addAccountToAccountManager(AccountService accountService, AccountManager accountManager, Scanner scanner){
        try {
            Account account = accountService.initiateCreatingAccount(scanner);// only add if persistence succeeds
            accountManager.addAccount(account);
        } catch (PersistenceException e) {
            System.err.println("Error saving account: " + e.getMessage());
            // maybe notify user or retry
        }
    }

}
