package com.bank.model.Accounts;

import com.bank.exceptions.InsuficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.exceptions.OverdraftExceededException;
import com.bank.exceptions.TransactionException;
import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import com.bank.utils.FormatUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    Customer vipCustomer;
    Account checkingAccount;
    Account savingAccount;

    int initialCheckingDeposit = 50;
    int initialSavingDeposit = 550;

    @BeforeEach
    void setUp() {
        vipCustomer = new PremiumCustomer("Leon", 15, "07878664755", "gisozi");
        checkingAccount = new CheckingAccount(initialCheckingDeposit, vipCustomer);

        Customer premiumCustomer = new PremiumCustomer("kageruka", 34, "07885082323", "Rwanda");
        savingAccount = new SavingsAccount(initialSavingDeposit, premiumCustomer);
    }

    // ---------------- CheckingAccount Tests ----------------

    @Test
    void testDepositBalanceAfterValidDeposit() throws InvalidAmountException {
        assertEquals("Leon", vipCustomer.getName());
        checkingAccount.deposit(50);
        assertEquals(initialCheckingDeposit + 50, checkingAccount.getBalance());
    }

    @Test
    void testDepositBalanceAfterValidWithdraw() throws TransactionException {
        checkingAccount.withdraw(40);
        assertEquals(initialCheckingDeposit - 40, checkingAccount.getBalance());
    }



    @Test
    void testWithdrawalWithinOverdraftLimit() throws TransactionException {
        checkingAccount.withdraw(1000);
        assertEquals(initialCheckingDeposit - 1000, checkingAccount.getBalance());
    }



    // ---------------- SavingsAccount Tests ----------------

    @Test
    void testValidDepositOnSavings() throws TransactionException {
        savingAccount.deposit(50);
        assertEquals(initialSavingDeposit + 50, savingAccount.getBalance());
    }



    @Test
    void testValidWithdrawalOnSavings() throws TransactionException {
        savingAccount.withdraw(40);
        assertEquals(initialSavingDeposit - 40, savingAccount.getBalance());
    }


}
