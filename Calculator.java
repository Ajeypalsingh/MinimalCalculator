package Java.Projects;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
     double firstInput = getInput("Enter first input :", scanner);
     double secondInput = 0;


        while(isRunning){
            System.out.println("Enter operator (+,-,*,/ or end): ");
            String operator = scanner.next();

            switch (operator) {
                case "+":
                    secondInput = getInput("Enter second input :", scanner);
                    firstInput = addition(firstInput, secondInput);
                    System.out.println(firstInput);
                    break;

                case "-":
                    secondInput = getInput("Enter second input", scanner);
                    firstInput = substraction(firstInput, secondInput);
                    System.out.println(firstInput);
                    break;

                case "*":
                    secondInput = getInput("Enter second input", scanner);
                    firstInput = multiplication(firstInput, secondInput);
                    System.out.println(firstInput);
                    break;

                case "/":
                    secondInput = getInput("Enter second input", scanner);
                    firstInput = division(firstInput, secondInput);
                    System.out.println(firstInput);
                    break;

                case "end" : 
                    System.out.println("Program ends");
                    isRunning = false;
                    break;
            
                default:
                System.out.println("Enter valid operator..");
                    break;
            }
           }
        }

        private static double addition (double inputOne, double inputTwo){
         double result = inputOne + inputTwo;
            return result;
        }  

        private static double substraction (double inputOne, double inputTwo){
            return inputOne - inputTwo;
        }

        private static double multiplication (double inputOne, double inputTwo){
            return inputOne * inputTwo;
        }

        private static double division (double inputOne, double inputTwo){
            return inputOne / inputTwo;
        }
        
        private static double getInput(String prompt, Scanner scanner){
            boolean isValid = false;
            double input = 0;

            while(!isValid){
                System.out.println(prompt);
                try {
                    input = scanner.nextInt();
                    isValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Try again! Please enter valid doubleeger value...");
                    scanner.next();
                }
            }
            return input;
        }
}
