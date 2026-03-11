package com.bank.utils;

import java.util.Scanner;

public class ConsoleUtils {
    // Waits until user presses Enter
    public static void waitForEnter(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine(); // waits for Enter

    }

    public static void waitForEnter(Scanner scanner, String message) {
        System.out.print(message);
        scanner.nextLine();

    }

}
