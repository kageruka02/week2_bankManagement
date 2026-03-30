package com.bank.utils;

import com.bank.exceptions.InvalidAccountException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidation {

    /**
     * takes age from console and only returns if it is > 0
     *
     * @param scanner accepts console input
     * @return age if it is valid
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

    public void validateContact(String contact){
        String patternString = "^(\\+25|25)?07\\d{8}$";
        boolean isMatch  =  Pattern.matches(patternString, contact);
        if (isMatch) return;
        throw new InputMismatchException("PLZ INPUT RWANDAN CONTACT");
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
     * @param scanner takes from the console
     * @return string if validation  pass
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
     * @return the input user if valid
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


    /**
     *
     * @param scanner gets from the console
     * @param message the message to be displayed if validation fails
     * @return the validated input
     */
    public String accountValidation(Scanner scanner, String message){

                String accountNumber = scanner.nextLine().trim();
                String accountValidationString = "^[Aa][Cc][Cc](00[1-9]|[1-9][0-9]{2})$";
        boolean isMatch = Pattern.matches(accountValidationString, accountNumber);
                if (isMatch){
                    return accountNumber;
                }
                throw new InvalidAccountException(message.toUpperCase());
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
