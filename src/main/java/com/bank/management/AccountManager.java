package com.bank.management;

import com.bank.exceptions.InvalidAccountException;
import com.bank.model.Accounts.Account;

public class AccountManager {

    private final Account[] accounts = new Account[50];
    private int accountCount = 0;

    /**
     *
     * @param toBeStoredAccount the account ready to be added to array
     */
    public void addAccount(Account toBeStoredAccount){
        if (toBeStoredAccount == null){
            return;
        }
        if (accountCount < 50){
            accounts[accountCount] = toBeStoredAccount;
            this.accountCount++;
        }
        else{
            System.out.println("reached maximum accounts");
        }
    }

    /**
     *
     * @param accountNumber the unique identifier of the account
     * @return the account if found or null if nothing found
     */
    public Account findAccount(String accountNumber){

        for(int index =0; index < this.accountCount; index++){
           Account acc = this.accounts[index];
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)){
                return acc;
            }
        }
        throw new InvalidAccountException("Error: Account not found. Please check the account number and try again");
    }

    /**
     * prints all the accounts available in the array
     */
    public void viewAllAccounts() {

        for(int index=0; index < accountCount ; index++ ){
            accounts[index].displayAccountDetails();
            System.out.println("_".repeat(70));

        }
    }

    /**
     *
     * @return sum of all accounts balance in our array
     */
    public double getTotalBalance(){
        double sumOfBalances = 0;
        for (int index=0; index < accountCount ; index++){
            sumOfBalances+=accounts[index].getBalance();
        }
        return sumOfBalances;
    }

    /**
     *
     * @return all accounts we have in our array
     */
    public int getAccountCount(){
        return this.accountCount;
    }

}
