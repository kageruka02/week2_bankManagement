package com.bank.utils.mappers;

import com.bank.model.Transactions.Transaction;
import com.bank.utils.FormatUtils;

public class TransactionMapper {

    // Convert Transaction → String (for saving to file)
    public static String toFileString(Transaction transaction) {
        return transaction.getTransactionId() + "," +
                transaction.getAccountNumber() + "," +
                transaction.getAmount() + "," +
                transaction.getType() + "," +
                transaction.getBalanceAfter() + "," +
                transaction.getTimeStampUTC();
    }

    // Convert String → Transaction (for reading from file)
    public static Transaction fromFileString(String line) {
        String[] parts = line.split(",");

        String transactionId = parts[0];
        String accountNumber = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String type = parts[3];
        double balanceAfter = Double.parseDouble(parts[4]);
        String timeStamp = parts[5];

        return new Transaction(transactionId, accountNumber, amount, type, balanceAfter, FormatUtils.changeFromStringToInstantUTC(timeStamp));
    }
}
