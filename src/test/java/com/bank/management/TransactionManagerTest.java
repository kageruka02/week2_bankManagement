package com.bank.management;

import com.bank.model.Accounts.CheckingAccount;
import com.bank.model.Accounts.SavingsAccount;
import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import com.bank.model.Transactions.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionManagerTest {

    private TransactionManager manager;
    private SavingsAccount savingAccount;


    @BeforeEach
    void setUp() {
        manager = new TransactionManager();
        Customer premiumCustomer = new PremiumCustomer("kageruka", 34, "07885082323", "Rwanda");
        double initialSavingDeposit = 1000.0;
        savingAccount = new SavingsAccount(initialSavingDeposit, premiumCustomer);
    }

    @Test
    void testAddTransactionFromSavingDeposit() {
       Transaction trx = new Transaction(
                savingAccount.getAccountNumber(), 200.0, "deposit", savingAccount.getBalance() + 200.0
        );
        trx.commit();
        manager.addTransaction(trx);

        assertEquals(1, manager.getTransactionCount());
        assertEquals(200.0, manager.calculateTotalDeposits(savingAccount.getAccountNumber()));
    }

    @Test
    void testCalculateTotalDeposits(){
        // Deposit 1
        Transaction trx1 = new Transaction(
                savingAccount.getAccountNumber(), 200.0, "deposit", savingAccount.getBalance() + 200.0);
        trx1.commit();
        manager.addTransaction(trx1);

        // Deposit 2
        Transaction trx2 = new Transaction(
                savingAccount.getAccountNumber(), 300.0, "deposit", savingAccount.getBalance() + 500.0);
        trx2.commit();
        manager.addTransaction(trx2);

        // Deposit 3
        Transaction trx3 = new Transaction(
                savingAccount.getAccountNumber(), 100.0, "deposit", savingAccount.getBalance() + 600.0);
        trx3.commit();
        manager.addTransaction(trx3);

        // Deposit 4
        Transaction trx4 = new Transaction(
                savingAccount.getAccountNumber(), 400.0, "deposit", savingAccount.getBalance() + 1000.0);
        trx4.commit();
        manager.addTransaction(trx4);

        // Withdrawal
        Transaction trx5 = new Transaction(
                savingAccount.getAccountNumber(), 250.0, "withdraw", savingAccount.getBalance() + 750.0);
        trx5.commit();
        manager.addTransaction(trx5);

        // Assertions
        assertEquals(5, manager.getTransactionCount(), "Should have 5 transactions recorded");

        double totalDeposits = manager.calculateTotalDeposits(savingAccount.getAccountNumber());
        assertEquals(1000.0, totalDeposits, "Total deposits should equal 1000.0");

        double totalWithdrawals = manager.calculateTotalWithdrawals(savingAccount.getAccountNumber());
        assertEquals(250.0, totalWithdrawals, "Total withdrawals should equal 250.0");
    }

}
