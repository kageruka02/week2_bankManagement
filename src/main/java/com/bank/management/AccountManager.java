package com.bank.management;

import com.bank.exceptions.InvalidAccountException;
import com.bank.model.Accounts.Account;

import java.util.*;

public class AccountManager {

    private final Map<String, Account> accounts = new HashMap<>();


    /**
     *
     * @param toBeStoredAccount the account ready to be added to array
     */
    public void addAccount(Account toBeStoredAccount){
        if (toBeStoredAccount != null){
            accounts.put(toBeStoredAccount.getAccountNumber().toLowerCase(), toBeStoredAccount);
        }
    }

    public void addAccounts(Map<String, Account> accountsToBeAdded){
        if (accountsToBeAdded != null){
            accounts.putAll(accountsToBeAdded);
        }

    }

    /**
     *
     * @param accountNumber the unique identifier of the account
     * @return the account if found or null if nothing found
     */
    public Account findAccount(String accountNumber) throws InvalidAccountException{

        Account acc = accounts.get(accountNumber.toLowerCase());
        if (acc != null) {
            return acc;
        }
        throw new InvalidAccountException("Error: Account not found. Please check the account number and try again");
    }

    /**
     * prints all the accounts available in the array
     */
    public void viewAllAccounts() {

        for (Account acc : accounts.values()) {
            acc.displayAccountDetails();
            System.out.println("_".repeat(70));
        }
    }

    public Collection<Account> getAllAccounts(){
        return  accounts.values();
    }

    /**
     *
     * @return sum of all accounts balance in our array
     */
    public double getTotalBalance(){
        double sumOfBalances = 0;
        for (Account acc : accounts.values()) {
            sumOfBalances += acc.getBalance();
        }
        return sumOfBalances;
    }

    /**
     *
     * @return all accounts we have in our array
     */
    public int getAccountCount(){
        return accounts.size();
    }

}
