package com.bank.utils;

import java.util.Scanner;

public class ConsoleUtils {
    // Waits until user presses Enter
    public static void waitForEnter(Scanner scanner) throws Exception {
        System.out.print("Press Enter to continue...");
        scanner.nextLine(); // waits for Enter
        Thread.sleep(200);
    }

    public static void waitForEnter(Scanner scanner, String message) throws Exception{
        System.out.print(message);
        scanner.nextLine();
        Thread.sleep(200);
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
