package com.bank.utils;

import java.util.Scanner;

public class ConsoleUtils {
    // Waits until user presses Enter
    public static void waitForEnter(Scanner scanner) {
        clearBuffer(scanner);
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine(); // waits for Enter

    }

    public static void clearBuffer(Scanner scanner) {
        try {
            if (System.in.available() > 0) {
                System.out.println("there is a bug solved by claude");
                scanner.nextLine();
            }
        } catch (Exception e) {
            // ignore
        }
    }

}
