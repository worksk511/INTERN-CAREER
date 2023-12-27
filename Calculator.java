import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Basic Calculator!");

        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the operation (see options): ");
        String operation = scanner.next();

        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        double result = performOperation(num1, operation, num2);

        System.out.println("Result: " + result);

        scanner.close();
    }

    // Function to perform the calculation based on the operation
    private static double performOperation(double num1, String operation, double num2) {
        switch (operation) {
            case "+":
                return add(num1, num2);
            case "-":
                return subtract(num1, num2);
            case "*":
                return multiply(num1, num2);
            case "/":
                return divide(num1, num2);
            case "sqrt":
                return sqrt(num1);
            case "cbrt":
                return cbrt(num1);
            case "pow":
                return pow(num1, num2);
            case "max":
                return max(num1, num2);
            case "min":
                return min(num1, num2);
            case "abs":
                return abs(num1);
            case "ceil":
                return ceil(num1);
            case "floor":
                return floor(num1);
            case "round":
                return round(num1);
            case "log":
                return log(num1);
            case "exp":
                return exp(num1);
            case "sin":
                return sin(num1);
            case "cos":
                return cos(num1);
            case "tan":
                return tan(num1);
            default:
                System.out.println("Error: Invalid operation.");
                System.exit(0);
                return 0; // This return statement will never be reached, but it's needed to satisfy the compiler.
        }
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            System.out.println("Error: Division by zero is not allowed.");
            System.exit(0);
            return 0;
        }
    }

    private static double sqrt(double a) {
        if (a >= 0) {
            return Math.sqrt(a);
        } else {
            System.out.println("Error: Cannot calculate square root of a negative number.");
            System.exit(0);
            return 0;
        }
    }

    private static double cbrt(double a) {
        return Math.cbrt(a);
    }

    private static double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    private static double max(double a, double b) {
        return Math.max(a, b);
    }

    private static double min(double a, double b) {
        return Math.min(a, b);
    }

    private static double abs(double a) {
        return Math.abs(a);
    }

    private static double ceil(double a) {
        return Math.ceil(a);
    }

    private static double floor(double a) {
        return Math.floor(a);
    }

    private static double round(double a) {
        return Math.round(a);
    }

    private static double log(double a) {
        if (a > 0) {
            return Math.log(a);
        } else {
            System.out.println("Error: Cannot calculate logarithm of a non-positive number.");
            System.exit(0);
            return 0;
        }
    }

    private static double exp(double a) {
        return Math.exp(a);
    }

    private static double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }

    private static double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }

    private static double tan(double a) {
        return Math.tan(Math.toRadians(a));
    }
}
