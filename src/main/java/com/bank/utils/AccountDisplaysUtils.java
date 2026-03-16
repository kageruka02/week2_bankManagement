package com.bank.utils;

import com.bank.management.AccountManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Accounts.CheckingAccount;
import com.bank.model.Accounts.SavingsAccount;

public class AccountDisplaysUtils {

    public  static void printAccountInTransaction(Account account){
        System.out.println("\nAccount Details:");
        System.out.println("Customer: "+ account.getCustomer().getName());
        System.out.println("Account Type:" + account.getAccountType());
        System.out.println("Current Balance: $"+ FormatUtils.formatAmount(account.getBalance()) );
    }

    public static void printAllAccountsToCLi(AccountManager accountManager) {

        System.out.println("\nACCOUNT LISTING");
        String accountNumberTitle = FormatUtils.giveStringFixedLength( "ACC NO", 10);
        String customerNameTitle = FormatUtils.giveStringFixedLength("CUSTOMER NAME", 20);
        String typeTitle = FormatUtils.giveStringFixedLength("TYPE", 12);
        String balanceTitle = FormatUtils.giveStringFixedLength("BALANCE" , 12);
        String statusTitle = FormatUtils.giveStringFixedLength("STATUS", 10);

        String formatted = String.format("%s | %s | %s | %s | %s", accountNumberTitle, customerNameTitle, typeTitle, balanceTitle, statusTitle
        );
        System.out.println(formatted);

        accountManager.viewAllAccounts();

        System.out.println("Accounts : "+accountManager.getAccountCount());


        System.out.println("Total Bank Balance: $"+ FormatUtils.formatAmount(accountManager.getTotalBalance()));
    }

    public static void displayAfterSuccessfulCreation(Account account){
        char check = '\u2713';
        System.out.println();
        System.out.println(check +" Account created successfully");
        System.out.println("  Account Number:" + account.getAccountNumber());
        String customerType =  account.getCustomer().getCustomerType();
        System.out.println("  Customer: " + account.getCustomer().getName() + " (" + customerType+ ")");
        System.out.println("  Account Type: " + account.getAccountType());
        System.out.println("  Initial Balance: $"+ FormatUtils.formatAmount(account.getBalance()) );
        if (account instanceof SavingsAccount){
            double interest = (double) Math.round(((SavingsAccount) account).getInterestRate() * 1000) /10;
            System.out.println("  Interest Rate: "+ interest + "%");
            System.out.println("  Minimum Balance: $"+ FormatUtils.formatAmount(((SavingsAccount) account).getMinimumBalance())) ;


        }
        else if (account instanceof CheckingAccount){
            System.out.println("  Overdraft Limit $"+ FormatUtils.formatAmount(((CheckingAccount) account).getOverdraftLimit())) ;

            if (customerType.equalsIgnoreCase("premium")){
                System.out.println("  Monthly Fee: $0.00 (WAIVED - Premium Customer)" );
            }
            else{
                System.out.println("  Monthly Fee: $"  + FormatUtils.formatAmount(((CheckingAccount) account).getMonthlyFee())) ;

            }

        }
        System.out.println("  Status: "+ account.getStatus());


    }

    public static void displayAccountDuringTransactionHistory(Account account){
        System.out.println("Account: "+account.getAccountNumber() + " - "+ account.getCustomer().getName());
        System.out.println("Account Type: "+ account.getAccountType());
        System.out.println("Current Balance: $"+ FormatUtils.formatAmount(account.getBalance()));
    }
}
