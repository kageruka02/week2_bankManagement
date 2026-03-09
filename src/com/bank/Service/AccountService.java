package com.bank.Service;

import com.bank.management.AccountManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Accounts.CheckingAccount;
import com.bank.model.Accounts.SavingsAccount;
import com.bank.model.Customers.Customer;

import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.CustomerInfo;
import com.bank.utils.InputValidation;

import java.util.Scanner;

public class AccountService {


    private final CustomerService customerService = new CustomerService();
    private final InputValidation inputValidation = new InputValidation();


    //TODO: improve this code
    public  Account initiateCreatingAccount(
            Scanner scanner
    ){
        System.out.println("ACCOUNT CREATION");
        System.out.println("__________________________________________\n");

        CustomerInfo customerInfo = readCustomerInfo(scanner);
        int customerType = readCustomerType(scanner);

        int accountChoice = readAccountType(scanner);

        double deposit = readAndValidateDeposit(accountChoice, scanner); // checks for just a deposit

        Customer customer = customerService.createCustomer(customerType, deposit,customerInfo); // create either regular customer or vip customer

        Account account = createAccount(accountChoice, customer, deposit);

        AccountDisplaysUtils.displayAfterSuccessfulCreation(account);

        return account;
    }

    private CustomerInfo readCustomerInfo(Scanner scanner){
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        System.out.print("Enter customer age: ");
        int age = inputValidation.getIntegerFromConsole(scanner, "put in a valid number");
        System.out.print("Enter customer contact: ");
        String contact = scanner.nextLine().trim();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine().trim();
        return new CustomerInfo(customerName, age, contact, address);
    }
    private int readCustomerType(Scanner scanner){
        System.out.println("\nCustomer type:");
        System.out.println("1. Regular Customer (Standard banking services)");
        System.out.println("2. Premium Customer (Enhanced benefits, min balance $10, 000)");
        System.out.println("\n Select type (1-2):");
       return inputValidation.getChoice(scanner, 1,2);
    }
    private int readAccountType(Scanner scanner){
        System.out.println("\nAccount type:");
        System.out.println("1. Saving Account");
        System.out.println("2. Checking Account");
        System.out.print("Select type (1-2): ");
        return inputValidation.getChoice(scanner, 1,2);
    }


    private double readAndValidateDeposit(int accountChoice, Scanner scanner){
        System.out.print("Enter initial deposit amount: $");
        while(true){

           double deposit = inputValidation.getDoubleFromConsole(scanner, "Enter a valid deposit amount");

            if(isValidInitialDeposit(accountChoice, deposit)){
                return deposit;
            }

            System.out.println("Deposit amount is not valid for this account type.");
            System.out.print("Enter initial deposit amount: $");
        }
    }


    private boolean isValidInitialDeposit(int accountChoice, double amount){

        if(accountChoice == 1){ // Savings
            return amount >= 500;
        }

        if(accountChoice == 2){ // Checking
            return amount > 0;
        }

        return false;
    }

    private Account createAccount(int accountChoice, Customer customer, double deposit){
        if (accountChoice == 1 && deposit >= 500){
            return new SavingsAccount(deposit, customer);
        }
            return new CheckingAccount(deposit, customer);

    }





    public void create5AccountstoBeginWith(AccountManager accountManager){

        int[] customerChoice = {1,2,1,2,1};
        double[] deposit = {5250, 3450, 15750, 890, 25300};
        String[] customerName = {"John Smith", "Sarah Johnson", "Michael Johnson", "Emily Brown", "David Wilson"};
        int[] age = {23,24,25,26,27};
        String[] contact = {"07884067854", "07884067854", "07884067854", "07884067854", "07884067854"};
        String[] address = {"GISOZI", "KAGUGU", "KIGALI","BURUNDI", "UGANDA" };


        for (int index=0; index < 5; index++){

            CustomerInfo customerInfo = new CustomerInfo(customerName[index], age[index], contact[index], address[index]);
            Customer  customer = customerService.createCustomer(customerChoice[index], deposit[index],customerInfo);
            Account account = createAccount(customerChoice[index], customer, deposit[index]);
            accountManager.addAccount(account);
            }

    }

}
