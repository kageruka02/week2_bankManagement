package com.bank.Service;

import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Accounts.SavingsAccount;
import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import com.bank.model.Customers.RegularCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    Customer customer;
    Customer customer1;
    Customer customer2;
    

    @BeforeEach
    void setUp(){
        customer = new RegularCustomer("leon", 14, "078098", "gisozi");
        customer1 = new PremiumCustomer("leon", 14, "078098", "gisozi");
        customer2 = new RegularCustomer("leon", 14, "078098", "gisozi");
    }

    @Test
    void transferSameAccountShouldFail() {
        Account account = new SavingsAccount(5000, customer);
        boolean result = transactionService.validateTransferAccounts(account, account);
        assertFalse(result);
    }

    @Test
    void transferInsufficientFundsShouldFail() {
        Account source = new SavingsAccount(500, customer1);       // only $500
        Account destination = new SavingsAccount(500, customer2);
        boolean result = transactionService.validateTransferAmount(source, destination, 1000); // try $1000
        assertFalse(result);
    }

    @Test
    void transferValidAmountShouldPass() {
        Account source = new SavingsAccount(5000, customer1);
        Account destination = new SavingsAccount(500, customer2);
        boolean result = transactionService.validateTransferAmount(source, destination, 1000);
        assertTrue(result);
    }

    @Test
    void executeTransferShouldUpdateBothBalances() {
        Account source = new SavingsAccount(5000, customer1);
        Account destination = new SavingsAccount(500, customer2);
        TransactionManager transactionManager = new TransactionManager();

        transactionService.executeTransfer(source, destination, 1000, transactionManager);

        assertEquals(4000, source.getBalance());
        assertEquals(1500, destination.getBalance());
        assertEquals(2, transactionManager.getTransactionCount()); // debit + credit
    }

}