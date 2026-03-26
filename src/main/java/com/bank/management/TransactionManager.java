package com.bank.management;

import com.bank.model.Transactions.Transaction;
import com.bank.model.enums.TransactionSort;
import com.bank.utils.FormatUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TransactionManager {


    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * adds a transaction to persistence
     *
     * @param toBeCreatedTransaction transaction created to be added to our array
     */
    public void addTransaction(Transaction toBeCreatedTransaction){
        if (toBeCreatedTransaction != null){
            transactions.add(toBeCreatedTransaction);
        }
    }

    public void addTransactionsCollection(List<Transaction> transactionsToBeAdded){
        if (transactionsToBeAdded != null && !transactionsToBeAdded.isEmpty()) {
            transactions.addAll(transactionsToBeAdded);
        }
    }

    /**
     * Collect all transactions for a given account number,
     * ordered from most recent to least recent by timeStamp.
     *
     * @param accountNumber the account number to search
     * @return list of transactions for that account
     */
    public List<Transaction> getTransactionsByAccount(String accountNumber, TransactionSort sortField) {
        TransactionSort effectiveSort =
                (sortField == null) ? TransactionSort.DATE : sortField;
        Comparator<Transaction> comparator = switch (effectiveSort) {
            case DATE -> Comparator.comparing(Transaction::getTimeStamp);
            case AMOUNT -> Comparator.comparing(Transaction::getAmount);
            case TYPE -> Comparator.comparing(Transaction::getType);
        };
        return transactions.stream()
                .filter(t -> t.getAccountNumber().equalsIgnoreCase(accountNumber))
                .sorted(comparator.reversed())
                .toList();
    }

    /**
     * Display all transactions for a given account number.
     *
     * @param accountNumber the account number to display
     * @return number of transactions displayed
     */
    public int viewTransactionsByAccount(String accountNumber, TransactionSort sort) {
        List<Transaction> result = getTransactionsByAccount(accountNumber, sort);
        System.out.println(result.stream().map(Transaction::getTransactionId).toList());
        if (result.isEmpty()) {
            System.out.println("-".repeat(50));
            System.out.println("No transactions recorded for this account.");
            System.out.println("-".repeat(50));
            return 0;
        }

        displayTitle();
        for (Transaction transaction : result) {
            transaction.displayTransactionDetails();
        }
        return result.size();
    }


    /**
     * display title before listing all accounts available
     */
    private void displayTitle(){
        System.out.println("TRANSACTION HISTORY");
        System.out.println("_".repeat(71));
        String trxIdTitle = FormatUtils.giveStringFixedLength("TXN ID", 10);
        String dateAndTimeTitle = FormatUtils.giveStringFixedLength("DATE/TIME", 22);
        String typeTitle = FormatUtils.giveStringFixedLength("TYPE", 12);
        String amountTitle = FormatUtils.giveStringFixedLength("AMOUNT", 15);
        String balanceTitle = FormatUtils.giveStringFixedLength("BALANCE", 12);
        String formater = String.format("%s | %s | %s | %s | %s ", trxIdTitle, dateAndTimeTitle, typeTitle, amountTitle, balanceTitle);
        System.out.println(formater);

    }

    /**
     *
     * @param accountNumber the account number to findBy
     * @return the sum of deposits the accountNumber has made
     */
    public double calculateTotalDeposits(String accountNumber){

        return transactions.stream()
                .filter(Objects::nonNull)
                .filter(t -> t.getType().equalsIgnoreCase("deposit"))
                .filter(t -> t.getAccountNumber().equalsIgnoreCase(accountNumber))
                .mapToDouble(Transaction::getAmount)
                .sum();

    }

    /**
     *
     * @param accountNumber the accountNumber to findby
     * @return the sum of all withdrawals the account has done
     */
    public double calculateTotalWithdrawals(String accountNumber){

        return transactions.stream()
                .filter(Objects::nonNull)
                .filter(t -> t.getType().equalsIgnoreCase("withdraw"))
                .filter(t -> t.getAccountNumber().equalsIgnoreCase(accountNumber))
                .mapToDouble(Transaction::getAmount)
                .sum();


    }

    public List<Transaction> getAllTransactions(){
        return transactions;
    }

    /**
     *
     * @return all transactions we have in our array
     */
    public int getTransactionCount(){
        return transactions.size();
    }

}
