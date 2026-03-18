package com.bank.model.Accounts;

import com.bank.exceptions.InsuficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.exceptions.OverdraftExceededException;
import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import com.bank.utils.FormatUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTest {

    private Account checkingAccount;
    private Account savingAccount;

    @BeforeEach
    void setUp() {
        Customer vipCustomer = new PremiumCustomer("Leon", 15, "07878664755", "gisozi");
        int initialCheckingDeposit = 50;
        checkingAccount = new CheckingAccount(initialCheckingDeposit, vipCustomer);

        Customer premiumCustomer = new PremiumCustomer("kageruka", 34, "07885082323", "Rwanda");
        int initialSavingDeposit = 550;
        savingAccount = new SavingsAccount(initialSavingDeposit, premiumCustomer);
    }

    // ---------------- CheckingAccount Exception Tests ----------------

    @Test
    void testInvalidWithdrawalThrowsInvalidAmountException() {
        InvalidAmountException exception =
                assertThrows(InvalidAmountException.class, () -> checkingAccount.withdraw(-50));
        assertNotNull(exception.getMessage());
    }

    @Test
    void testWithdrawalExceedingOverdraftLimitThrowsOverdraftExceededException() {
        OverdraftExceededException exc =
                assertThrows(OverdraftExceededException.class, () -> checkingAccount.withdraw(4000));
        assertTrue(exc.getMessage().contains(FormatUtils.formatAmount(checkingAccount.getBalance())));
    }

    // ---------------- SavingsAccount Exception Tests ----------------

    @Test
    void testInvalidDepositOnSavings() {
        assertThrows(InvalidAmountException.class, () -> savingAccount.deposit(-50));
    }

    @Test
    void testWithdrawalReachingLessThanMinimumBalanceThrowsInsufficientFundsException() {
        assertThrows(InsuficientFundsException.class, () -> savingAccount.withdraw(500));
    }
}
