package com.bank.Service;

import com.bank.exceptions.PersistenceException;
import com.bank.management.AccountManager;
import com.bank.model.Accounts.Account;
import com.bank.model.Transactions.Transaction;
import com.bank.utils.mappers.AccountMapper;
import com.bank.utils.mappers.TransactionMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Stream;

public class FilePersistenceService {
    private static final Path ACCOUNTS_FILE = Paths.get("data/accounts.txt");
    private static final Path TRANSACTIONS_FILE = Paths.get("data/transactions.txt");


    // Load accounts with try-with-resources
    public static synchronized Map<String, Account> loadAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        if (Files.exists(ACCOUNTS_FILE)) {
            try (Stream<String> lines = Files.lines(ACCOUNTS_FILE)) {
                lines.map(AccountMapper::fromFileString)
                        .forEach(acc -> accounts.put(acc.getAccountNumber(), acc));
            } catch (IOException e) {
                System.out.println("Failed to load accounts");
            }
        }
        return accounts;
    }

    public static synchronized  void persistAllAccounts(Collection<Account> accounts) throws PersistenceException {

        List<String> serialized = accounts.stream().map(AccountMapper::toFileString).toList();
        try{
            createDirectoryIfNotExist(ACCOUNTS_FILE);
            Files.write(ACCOUNTS_FILE, serialized, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new PersistenceException("Failed to persist accounts :" + e.getMessage());
        }





    }
    // Load transactions with try-with-resources
    public static synchronized List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        if (Files.exists(TRANSACTIONS_FILE)) {
            try (Stream<String> lines = Files.lines(TRANSACTIONS_FILE)) {
                lines.map(TransactionMapper::fromFileString)
                        .forEach(transactions::add);
            } catch (IOException e) {
                System.err.println("Error loading transactions: " + e.getMessage());
            }
        }
        return transactions;
    }

    public static synchronized void appendAccount(Account account) throws PersistenceException {
        // Add a newline at the end of each record
        String record = AccountMapper.toFileString(account) + System.lineSeparator();
        try {
            createDirectoryIfNotExist(ACCOUNTS_FILE);
            Files.write(ACCOUNTS_FILE,
                    record.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException("Failed to persist account" + e.getMessage());
        }
    }

    public static synchronized void appendTransaction(Transaction transaction) {
        // Add a newline at the end of each record
        String record = TransactionMapper.toFileString(transaction) + System.lineSeparator();
        try {
            createDirectoryIfNotExist(TRANSACTIONS_FILE);
            Files.write(TRANSACTIONS_FILE,
                    record.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error appending transaction: " + e.getMessage());
        }
    }

    private static void createDirectoryIfNotExist(Path path) throws IOException {
        Path directory = path.getParent();
        if (directory != null){
            Files.createDirectories(directory);
        }
    }

}
