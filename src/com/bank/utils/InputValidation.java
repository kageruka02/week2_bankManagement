package com.bank.utils;

import java.util.Scanner;

public class InputValidation {


    public int getAgeFromConsole(Scanner scanner, String message){
        while(true){

            try {
                int age =  Integer.parseInt(scanner.nextLine().trim());
                if (age > 0){
                    return age;
                }
            } catch (NumberFormatException ignored) {

            }
            System.out.println(message.toUpperCase());
            System.out.print("Enter customer age: ");
        }
    }

    public String getStringFromConsole(Scanner scanner, String message){

        while(true){
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()){
                return input;
            }
            System.out.print(message);
        }
    }

    public  int getChoice(Scanner scanner, int min, int max, String message){

        while(true){

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == min || choice == max) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // ignore, will print message below
            }
            System.out.print("ENTER VALID OPTION \n"+ message);
        }
    }

    public  int rangeChoice(Scanner scanner, int min, int max, String s){

        while(true){
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // ignore, will print message below
            }
            System.out.print("ENTER VALID OPTION \n"+ s);
        }
    }

    public double getPositiveAmount( Scanner scanner, String message){
        while(true){
            try {
               double inputAmount = Double.parseDouble(scanner.nextLine().trim());
                if (inputAmount > 0) {
                    return inputAmount;
                }
                if (inputAmount <= 0){
                    System.out.print("YOU CAN NOT INPUT AMOUNT LESS THAN OR EQUAL TO 0 TRY AGAIN\n"+message );
                }
            } catch (NumberFormatException ignored) {
                System.out.print("INPUT VALID AMOUNT\n"+ message);
            }
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
                System.out.print("Enter Account Number: ");


        }
    }


    public String getCharFrom2Chars(Scanner scanner, String x, String y, String message){

        while(true){

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase(x) || input.equalsIgnoreCase(y)){
                return input;
            }
            else{
                System.out.print("PLEASE CONFIRM THE TRANSACTION WITH Y(IF YOU APPROVE) OR N(IF YOU DON'T)\n"+ message);
            }
        }
    }

}
