package Java.Projects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class EnhancedCalculator {

    private static List<String> history = new LinkedList<>();
    private static double memory = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Enter expression or command (history, reset, mem+, mem-, memr, memc, end): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("end")) {
                System.out.println("Calculation ends");
                isRunning = false;
            } else if (input.equalsIgnoreCase("history")) {
                showHistory();
            } else if (input.equalsIgnoreCase("reset")) {
                history.clear();
                System.out.println("Calculations got reset");
            } else if (input.equalsIgnoreCase("mem+")) {
                System.out.println("Enter expression to add to memory:");
                String expression = scanner.nextLine().trim();
                double result = evaluate(expression, scanner);
                memory += result;
                history.add("mem+ " + result + " (Memory now: " + memory + ")");
                System.out.println("Memory stored: " + memory);
            } else if (input.equalsIgnoreCase("mem-")) {
                System.out.println("Enter expression to subtract from memory:");
                String expression = scanner.nextLine().trim();
                double result = evaluate(expression, scanner);
                memory -= result;
                history.add("mem- " + result + " (Memory now: " + memory + ")");
                System.out.println("Memory stored: " + memory);
            } else if (input.equalsIgnoreCase("memr")) {
                System.out.println("Memory recall: " + memory);
            } else if (input.equalsIgnoreCase("memc")) {
                memory = 0;
                System.out.println("Memory cleared");
            } else {
                double result = evaluate(input, scanner);
                history.add("Expression -> " + input + " = " + result);
                System.out.println("Result: " + result);
            }
        }

    }

    private static double evaluate(String input, Scanner scanner) {
        try {
            return new Expression(input).evaluate();
        } catch (NumberFormatException e) {
            System.out.println("Invalid expression");
            return 0;
        }
    }

    private static void showHistory() {
        System.out.println("Calculation history : ");
        for (String record : history) {
            System.out.println(record);
        }
    }

    static class Expression {
        private String expression;

        public Expression(String expression) {
            this.expression = expression;
        }

        public double evaluate() {
            List<String> tokens = tokenize(expression);
            List<String> postfix = infixToPostfix(tokens);
            return evaluatePostfix(postfix);
        }

        private List<String> tokenize(String expression) {
            List<String> tokens = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/^()%", true);

            while (tokenizer.hasMoreTokens()) {
                tokens.add(tokenizer.nextToken().trim());
            }

            return tokens;
        }

        private List<String> infixToPostfix(List<String> tokens) {
            List<String> postfix = new ArrayList<>();
            Stack<String> stack = new Stack<>();

            for (String token : tokens) {
                if (isNumber(token)) {
                    postfix.add(token);
                } else if (token.equals("(")) {
                    stack.push(token);
                } else if (token.equals(")")) {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                        postfix.add(stack.pop());
                    }
                    stack.push(token);
                }

            }
            while (!stack.isEmpty()) {
                postfix.add(stack.pop());
            }

            return postfix;
        }
    }

    private static double evaluatePostfix(List<String> tokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;

                    case "-":
                        stack.push(a - b);
                        break;

                    case "*":
                        stack.push(a * b);
                        break;

                    case "/":
                        stack.push(a / b);
                        break;

                    case "%":
                        stack.push(a % b);
                        break;

                    case "^":
                        stack.push(Math.pow(a, b));
                        break;
                    default:
                        break;
                }
            }
        }

        return stack.pop();
    }

    private static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }
}
