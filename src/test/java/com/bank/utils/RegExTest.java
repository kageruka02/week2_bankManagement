package com.bank.utils;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegExTest {


    private final InputValidation validator = new InputValidation();

    @Test
    void validLocalNumberStartingWithZero() {
        assertDoesNotThrow(() -> validator.validateContact("0788123456"));
    }


    @Test
    void validInternationalNumberWithCountryCode() {
        assertDoesNotThrow(() -> validator.validateContact("+250788123456"));
    }

    @Test
    void validNumberWithoutPlusButWithCountryCode() {
        assertDoesNotThrow(() -> validator.validateContact("250788123456"));
    }

    @Test
    //testing +0788431278
    void invalidNumber(){
        assertThrows((InputMismatchException.class), () -> {
            validator.validateContact("+0788431278");
        });
    }

    @Test
    void testNumberStartingWith7(){
        assertThrows((InputMismatchException.class), () -> {
            validator.validateContact("788313390");
        });
    }


    @Test
    void invalidNumberTooShort() {
        assertThrows(InputMismatchException.class,
                () -> validator.validateContact("07881234"));
    }

    @Test
    void invalidNumberTooLong() {
        assertThrows(InputMismatchException.class,
                () -> validator.validateContact("0788123456789"));
    }

    @Test
    void invalidNumberWithLetters() {
        assertThrows(InputMismatchException.class,
                () -> validator.validateContact("0788ABC456"));
    }

    @Test
    void invalidNumberWrongPrefix() {
        assertThrows(InputMismatchException.class,
                () -> validator.validateContact("1988123456"));
    }

    @Test
    void invalidNumberMissingCountryCodeDigits() {
        assertThrows(InputMismatchException.class,
                () -> validator.validateContact("+25078"));
    }
    @Test
    void invalidNumberWithoutMtnOrAirtel(){
        assertThrows(InputMismatchException.class, () -> {
            validator.validateContact("0775060545");
        });
    }
}
