package com.pv;

import java.io.File;
import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        // try {
        //     // int result = 10 / 0;
        //     // System.out.println("Result: " + result);

        //     String str = null;
        //     System.out.println("String length: " + str.length());
        // } catch (NullPointerException e) {
        //     System.out.println("Caught an NullPointerException: " + e.getMessage());
        // } finally {
        //     System.out.println("Execution of the try-catch block is complete.");
        // }


        //read file Main.java
        File file = new File("Main.java");
        // read contents
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        //  String str = null;
        // System.out.println("String length: " + str.length());

        System.out.println("Allz well!");

        try{
            validateInput(-5);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
        
    }


    public static void validateInput(int number) throws InvalidInputException {
        if (number < 0) {
            throw new InvalidInputException("Input number cannot be negative: " + number);
        }
    }
}
