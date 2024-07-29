package Java.Projects;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        int firstInput = getInput("Enter first input :", scanner);
        int secondInput = 0;


        while(isRunning){
            System.out.println("Enter operator (+,- or end): ");
            String operator = scanner.next();

            switch (operator) {
                case "+":
                    secondInput = getInput("Enter second input :", scanner);
                    firstInput = Addition(firstInput, secondInput);
                    System.out.println(firstInput);
                    break;

                case "-":
                    secondInput = getInput("Enter second input", scanner);
                    firstInput = Substraction(firstInput, secondInput);
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

        private static int Addition(int inputOne, int inputTwo){
            int result = inputOne + inputTwo;
            return result;
        }  

        private static int Substraction(int inputOne, int inputTwo){
            return inputOne - inputTwo;
        }
        
        private static int getInput(String prompt, Scanner scanner){
            boolean isValid = false;
            int input = 0;

            while(!isValid){
                System.out.println(prompt);
                try {
                    input = scanner.nextInt();
                    isValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Try again! Please enter valid integer value...");
                    scanner.next();
                }
            }
            return input;
        }
}
