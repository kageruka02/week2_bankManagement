package com.bank.Service;

import com.bank.exceptions.PersistenceException;
import com.bank.management.AccountManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Accounts.CheckingAccount;
import com.bank.model.Accounts.SavingsAccount;
import com.bank.model.Customers.Customer;

import com.bank.utils.AccountDisplaysUtils;
import com.bank.utils.CustomerInfo;
import com.bank.utils.InputValidation;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountService {


    private final CustomerService customerService = new CustomerService();
    private final InputValidation inputValidation = new InputValidation();


    /**
     * handles creation of account from console
     *
     * @param scanner getting inputs from the console(user)
     * @return Account created or null if nothing was created
     */
    public  Account initiateCreatingAccount(
            Scanner scanner
    )  {
        System.out.println("ACCOUNT CREATION");
        System.out.println("__________________________________________\n");

        CustomerInfo customerInfo = readCustomerInfo(scanner);

        int customerType = readCustomerType(scanner);

        int accountChoice = readAccountType(scanner);

        double deposit = readAndValidateDeposit(accountChoice, scanner); // checks for just a deposit

        Customer customer = customerService.createCustomer(customerType, deposit,customerInfo); // create either regular customer or vip customer

        Account account = createAccount(accountChoice, customer, deposit);

        //display account details on console after successful creation
        AccountDisplaysUtils.displayAfterSuccessfulCreation(account);

        return account;
    }

    /**
     *
     * takes name, age, contact, address from console and return a bound object
     *
     * @param scanner takes customer details needed
     * @return an object which bind the customer information
     */
    private CustomerInfo readCustomerInfo(Scanner scanner){


        String customerName;
        while (true) {
            try {
                System.out.print("Enter customer name: ");
                customerName = inputValidation.getStringFromConsole(scanner);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        int age;
        while (true) {
            try {
                System.out.print("Enter customer age: ");
                age = inputValidation.getAgeFromConsole(scanner);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }




        String contact;
        while (true) {
            try {
                System.out.print("Enter customer contact: ");
                contact = inputValidation.getContactFromConsole(scanner);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }





        String address;
        while (true) {
            try {
                System.out.print("Enter customer address: ");
                address = inputValidation.getStringFromConsole(scanner);
                break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        return new CustomerInfo(customerName, age, contact, address);
    }

    /**
     * read customerType and validates
     *
     * @param scanner  to get customerType from the console
     * @return  the number chosen by the user if the number is valid
     */
    private int readCustomerType(Scanner scanner){
        System.out.println("\nCustomer type:");
        System.out.println("1. Regular Customer (Standard banking services)");
        System.out.println("2. Premium Customer (Enhanced benefits, min balance $10, 000)");


        while(true){
            try{
                System.out.print("\nSelect type (1-2): ");
                return inputValidation.getChoice(scanner, 1,2,"Select type (1-2): ") ;
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }

    }
    private int readAccountType(Scanner scanner){
        System.out.println("\nAccount type:");
        System.out.println("1. Saving Account (Interest: 3.5%, Min Balance: $500)");
        System.out.println("2. Checking Account (Overdraft: $1,000, Monthly Fee: $10)");

        while(true){
            try{
                System.out.print("\nSelect type (1-2): ");
                return inputValidation.getChoice(scanner, 1,2,"Select type (1-2): ") ;
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * read deposit amount from the console  , validate  and make sure it is valid relative to the account
     *
     * @param accountChoice 1-saving 2-checking
     * @param scanner  takes deposit from console
     * @return the deposit if it is valid relative to accountType
     */

    private double readAndValidateDeposit(int accountChoice, Scanner scanner){
        System.out.print("\nEnter initial deposit amount: $");
        while(true){

           double deposit = inputValidation.getDoubleFromConsole(scanner, "Enter a valid initial deposit amount: ");//validate amount from console

            if(isInitialDepositValid(accountChoice, deposit)){
                return deposit; // if the deposit amount is valid relative to accountType
            }

            System.out.println("Deposit amount is not valid for this account type.");
            System.out.print("Enter initial deposit amount: $ ");
        }
    }

    /**
     * checks if the amount is relative to account type
     * @param accountChoice  1- saving , 2 - checking
     * @param amount cash to deposit
     *
     */
    private boolean isInitialDepositValid(int accountChoice, double amount){

        if(accountChoice == 1){ // Savings account
            return amount >= 500;
        }

        if(accountChoice == 2){ // Checking account
            return amount > 0;
        }

        return false;
    }

    /**
     *
     *
     * @param accountChoice for accountChoice 1 - savingsAccount, 2 - checking account
     * @param customer helps to create the account
     * @param deposit amount to be deposited
     * @return account created
     */
    private Account createAccount(int accountChoice, Customer customer, double deposit){
        if (accountChoice == 1 && deposit >= 500){
            return new SavingsAccount(deposit, customer);
        }
            return new CheckingAccount(deposit, customer);

    }

    public void loadAccountsFromFile(AccountManager accountManager){
       accountManager.addAccounts(FilePersistenceService.loadAccounts()); ;
    }

}
