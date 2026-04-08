package com.bank.concurrent;

import com.bank.management.AccountManager;
import com.bank.management.TransactionManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Accounts.SavingsAccount;
import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ThreadServiceTest {


    Account account;
    TransactionManager transactionManager = new TransactionManager();
    AccountManager accountManager;
    TransactionConcurrentExecutor executor = new TransactionConcurrentExecutor();

    @BeforeEach
    public void setUp(){
        Customer customer = new PremiumCustomer("leon", 11, "gisozi", "kagugu");
        accountManager = mock(AccountManager.class);
        account = new SavingsAccount(200000, customer);
        when(accountManager.findAccount("ACC123"))
                .thenReturn(account);
    }

    @Test
    void concurrentDepositsOnSameAccountShouldBeAccurate() throws InterruptedException {

        // submit 10 deposits of $100 concurrently
        for (int i = 0; i < 200; i++) {
            executor.simulateDeposits(account,  transactionManager);
        }

        executor.stopTheExecutor(); // waits for all threads to finish

        assertEquals(203000, account.getBalance()); // 1000 + (10 x 100)

    }

    @Test
    void concurrentWithdrawalOnSameAccountShouldBeAccurate() throws InterruptedException{
        // submit 10 deposits of $100 concurrently
        for (int i = 0; i < 200; i++) {
            executor.simulateWithdrawals(account,  transactionManager);
        }

        executor.stopTheExecutor(); // waits for all threads to finish

        assertEquals(197000, account.getBalance());
    }
}


