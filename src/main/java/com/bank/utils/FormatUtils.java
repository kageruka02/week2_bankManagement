package com.bank.utils;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FormatUtils {


    public static String giveStringFixedLength(
            String value, int length
    ){

        String toBeSliced = value + " ".repeat(length);
        return toBeSliced.substring(0, length);
    }

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    public static String formatAmount(double amount) {
        return DECIMAL_FORMAT.format(amount);
    }

}
