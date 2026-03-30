package com.bank.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionLogger {
    public static final AtomicInteger counter = new AtomicInteger(1);
}
