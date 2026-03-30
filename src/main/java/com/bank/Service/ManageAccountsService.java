package com.bank.Service;

import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.ConsoleUtils;
import com.bank.utils.InputValidation;

import java.util.Scanner;

public class ManageAccountsService {

    private final AccountService accountService = new AccountService();
    private final TransactionService transactionService = new TransactionService();
    private final InputValidation inputValidation = new InputValidation();

    public void manageAccountsMenu(Scanner scanner, AccountManager accountManager, TransactionManager transactionManager) {
        while (true) {
            printManageAccountsMenu();
            int choice = inputValidation.rangeChoice(scanner, 1, 4, "Enter choice: ");

            switch (choice) {
                case 1:
                    accountManager.addAccount(accountService.initiateCreatingAccount(scanner));
                    ConsoleUtils.waitForEnter(scanner);
                    break;
                case 2:
                    AccountDisplaysUtils.printAllAccountsToCLi(accountManager);
                    ConsoleUtils.waitForEnter(scanner);
                    break;
                case 3:
                    transactionService.initiateTransfer(scanner, accountManager, transactionManager);
                    ConsoleUtils.waitForEnter(scanner);
                    break;
                case 4:
                    return; // back to main menu
            }
        }
    }

    private void printManageAccountsMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("       MANAGE ACCOUNTS");
        System.out.println("=".repeat(40));
        System.out.println("1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Transfer Between Accounts");
        System.out.println("4. Back to Main Menu");
        System.out.println("=".repeat(40));
        System.out.print("Enter choice: ");
    }
}