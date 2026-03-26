package com.bank.utils;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest {
        private final InputValidation validator = new InputValidation();

        @Test
        void testValidAge() {
            Scanner scanner = new Scanner("25\n");
            int age = validator.getAgeFromConsole(scanner);
            assertEquals(25, age);
        }

        @Test
        void testAgeZeroThrowsException() {
            Scanner scanner = new Scanner("0\n");
            assertThrows(InputMismatchException.class, () -> {
                validator.getAgeFromConsole(scanner);
            });
        }

    @Test
    void testValidContact() {
        Scanner scanner = new Scanner("07878664755\n");
        String contact = validator.getContactFromConsole(scanner);
        assertEquals("07878664755", contact);
    }

    @Test
    void testValidContactWithWhitespace() {
        Scanner scanner = new Scanner("   +250788506789   \n");
        String contact = validator.getContactFromConsole(scanner);
        assertEquals("+250788506789", contact);
    }

    @Test
    void testEmptyContactThrowsException() {
        Scanner scanner = new Scanner("\n");
        assertThrows(InputMismatchException.class, () -> {
            validator.getContactFromConsole(scanner);
        });
    }

    @Test
    void testOnlyWhitespaceThrowsException() {
        Scanner scanner = new Scanner("     \n");
        assertThrows(InputMismatchException.class, () -> {
            validator.getContactFromConsole(scanner);
        });
    }
}