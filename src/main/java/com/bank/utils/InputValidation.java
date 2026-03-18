package com.bank.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {

    /**
     * takes age from console and only returns if it is > 0
     *
     * @param scanner
     * @return
     */
    public int getAgeFromConsole(Scanner scanner){

            try {
                int age =  Integer.parseInt(scanner.nextLine().trim());
                if (age > 0){
                    return age;
                }
                throw new InputMismatchException("age must be greater than 0");
            } catch (NumberFormatException ignored) {
                throw new InputMismatchException("Invalid age format");
            }


    }

    public String getContactFromConsole(Scanner scanner){
        String input  = scanner.nextLine().trim();
        if (!input.isEmpty()){
            return input;
        }
        throw new InputMismatchException("give valid contact");
    }

    private  boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private  boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     *  takes string from console and only returns if the string is not empty
     *
     * @param scanner
     * @return
     */
    public String getStringFromConsole(Scanner scanner){


            String input = scanner.nextLine().trim();
            if (input.isEmpty() ){
                throw new InputMismatchException("Input cannot be empty");
            }
            if (isDouble(input) || isInteger(input)){
                throw new InputMismatchException("Plz provide a name not a number");
            }
            return input;

    }

    /**
     *takes the input from user and checks if it is equal to allowedValueA or allowedValueB
     *
     * @param scanner takes input from console
     * @param allowedValueA choice the input maybe equal to
     * @param allowedValueB choice the input maybe equal to
     * @param message will be printed if the user enters invalid choice
     * @return
     */
    public  int getChoice(Scanner scanner, int allowedValueA, int allowedValueB, String message){

      try{
          int choice = Integer.parseInt(scanner.nextLine().trim());
          if (choice == allowedValueA || choice == allowedValueB) {
              return choice;
          }
          throw new InputMismatchException("Enter valid number");
      }
             catch (NumberFormatException e) {
                // ignore, will print message below
        throw new InputMismatchException("ENTER A NUMBER PLZ");
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
                System.out.print("INVALID AMOUNT\n" + message);
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
