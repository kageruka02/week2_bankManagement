package com.bank.utils;

import java.util.Scanner;

public class InputValidation {


    public int getIntegerFromConsole(Scanner scanner, String message){
        while(true){

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(message);
            }
        }
    }

    public  int getChoice(Scanner scanner, int min, int max){

        while(true){

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == min || choice == max) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // ignore, will print message below
            }
            System.out.println("Enter valid option");
        }
    }

    public  int rangeChoice(Scanner scanner,int min, int max ){

        while(true){
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // ignore, will print message below
            }
            System.out.println("Enter valid option");
        }
    }

    public double getPositiveAmount(double amount, Scanner scanner, String message){
        while(true){
            try {
                double inputAmount = Double.parseDouble(scanner.nextLine().trim());
                if (inputAmount > amount) {
                    return inputAmount;
                }
            } catch (NumberFormatException e) {
                System.out.println(message);
            }
            System.out.println("Enter amount: $");
        }
    }

    public  double getDoubleFromConsole(Scanner scanner, String message){


        while(true){
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(message);
            }

        }

    }



    public String accountValidation(Scanner scanner, String message){

        while(true){

                String accountNumber = scanner.nextLine().trim();
                if (accountNumber.toUpperCase().startsWith("ACC")){
                    return accountNumber;
                }

                System.out.println(message);
                System.out.println("Enter Account Number: ");


        }
    }


    public String getCharFrom2Chars(Scanner scanner, String x, String y, String message){

        while(true){

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(x) || input.equalsIgnoreCase(y)){
                return input;
            }
            else{
                System.out.println(message);
            }
        }
    }

}
