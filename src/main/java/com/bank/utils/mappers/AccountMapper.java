package com.bank.utils.mappers;

import com.bank.model.Accounts.Account;
import com.bank.model.Accounts.CheckingAccount;
import com.bank.model.Accounts.SavingsAccount;
import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import com.bank.model.Customers.RegularCustomer;

public class AccountMapper {

    public static String toFileString(Account account) {
        Customer customer = account.getCustomer();

        return account.getAccountNumber() + "," +
                account.getAccountType() + "," +
                account.getBalance() + "," +
                account.getStatus() + "," + // <-- added status
                customer.getCustomerId() + "," +
                customer.getName() + "," +
                customer.getAge() + "," +
                customer.getContact() + "," +
                customer.getAddress() + "," +
                customer.getCustomerType();
    }

    // Convert String → Account (for loading from file)
    public static Account fromFileString(String line) {
        String[] parts = line.split(",");

        String accountNumber = parts[0];
        String type = parts[1];
        double balance = Double.parseDouble(parts[2]);
        String status = parts[3]; // <-- read status

        Customer customer = getCustomerFromString(parts);

        if (type.equalsIgnoreCase("Savings")) {
            return new SavingsAccount(accountNumber, customer, balance, status);
        } else {
            return new CheckingAccount(accountNumber, customer, balance, status);
        }
    }

    private static Customer getCustomerFromString(String[] parts) {
        String customerId = parts[4];
        String name = parts[5];
        int age = Integer.parseInt(parts[6]);
        String contact = parts[7];
        String address = parts[8];
        String customerType = parts[9];

        Customer customer;
        if (customerType.equalsIgnoreCase("premium")) {
            customer = new PremiumCustomer(customerId, name, age, contact, address);
        } else {
            customer = new RegularCustomer(customerId, name, age, contact, address);
        }
        return customer;
    }
}
