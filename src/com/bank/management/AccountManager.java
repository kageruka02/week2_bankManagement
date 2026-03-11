package com.bank.management;

import com.bank.model.Accounts.Account;

public class AccountManager {

    private final Account[] accounts = new Account[50];
    private int accountCount = 0;

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

    public Account findAccount(String accountNumber){

        for(int index =0; index < this.accountCount; index++){
           Account acc = this.accounts[index];
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)){
                return acc;
            }
        }
        return null;
    }

    public void viewAllAccounts() {

        for(int index=0; index < accountCount ; index++ ){
            accounts[index].displayAccountDetails();
            System.out.println("_".repeat(70));

        }
    }

    public double getTotalBalance(){
        double sumOfBalances = 0;
        for (int index=0; index < accountCount ; index++){
            sumOfBalances+=accounts[index].getBalance();
        }
        return sumOfBalances;
    }
    public int getAccountCount(){
        return this.accountCount;
    }

}
