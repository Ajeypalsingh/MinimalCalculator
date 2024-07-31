package Java.Projects;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static List<String> history = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            double firstInput = getInput("Enter first input :", scanner);
            double secondInput = 0;

            System.out.println("Enter operator (+, -, *, /, %, sqrt, history, reset or end): ");
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
                    if (secondInput == 0) {
                        System.out.println("Can't divide by 0");
                        secondInput = getInput("Enter second input", scanner);
                        firstInput = division(firstInput, secondInput);
                        System.out.println(firstInput);
                    } else {
                        firstInput = division(firstInput, secondInput);
                        System.out.println(firstInput);
                    }
                    break;

                case "sqrt":
                    firstInput = sqrt(firstInput);
                    System.out.println(firstInput);
                    break;

                case "%":
                    secondInput = getInput("Enter second input", scanner);
                    firstInput = firstInput % secondInput;
                    System.out.println(firstInput);
                    break;

                case "history":
                    showHistory();
                    break;

                case "end":
                    System.out.println("Calculation ends");
                    isRunning = false;
                    break;

                case "reset":
                    System.out.println("Calculations got reset");
                    break;

                default:
                    System.out.println("Enter valid operator..");
                    break;
            }

        }
    }

    private static double addition(double inputOne, double inputTwo) {
        double result = inputOne + inputTwo;
        history.add(inputOne + " + " + inputTwo + " = " + result);
        return result;
    }

    private static double substraction(double inputOne, double inputTwo) {
        double result = inputOne - inputTwo;
        history.add(inputOne + " - " + inputTwo + " = " + result);
        return result;
    }

    private static double multiplication(double inputOne, double inputTwo) {
        double result = inputOne * inputTwo;
        history.add(inputOne + " * " + inputTwo + " = " + result);
        return result;
    }

    private static double division(double inputOne, double inputTwo) {
        double result = inputOne / inputTwo;
        history.add(inputOne + " / " + inputTwo + " = " + result);
        return result;
    }

    private static double sqrt(double inputOne) {
        double result = Math.sqrt(inputOne);
        history.add("Square root of " + inputOne + "=" + result);
        return result;
    }

    private static double modulus(double inputOne, double inputTwo) {
        double result = inputOne % inputTwo;
        history.add(inputOne + " modulus " + inputTwo + " = " + result);
        return result;
    }

    private static void showHistory() {
        System.out.println("Calculation history");

        for (String record : history) {
            System.out.println(record);
        }
    }

    private static double getInput(String prompt, Scanner scanner) {
        boolean isValid = false;
        double input = 0;

        while (!isValid) {
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
