package com.bank.utils;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormatUtils {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    public static String giveStringFixedLength(
            String value, int length
    ){

        String toBeSliced = value + " ".repeat(length);
        return toBeSliced.substring(0, length);
    }



    public static String formatAmount(double amount) {
        return DECIMAL_FORMAT.format(amount);
    }

    public static String changeFromInstantToStringSystemZone(Instant instant){
        DateTimeFormatter format =   DateTimeFormatter.ofPattern("dd-MM-yy h:mma").withZone(ZoneId.systemDefault());
        return  format.format(instant);
    }

    public static String changeFromInstanttoStringUTC(Instant instant){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy h:mma").withZone(ZoneId.of("UTC"));
        return format.format(instant);
    }

    public static Instant changeFromStringToInstantUTC(String inputTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy h:mma");
        LocalDateTime localDateTime = LocalDateTime.parse(inputTime, formatter);
        return localDateTime.atZone(ZoneId.of("UTC")).toInstant();
    }

}
