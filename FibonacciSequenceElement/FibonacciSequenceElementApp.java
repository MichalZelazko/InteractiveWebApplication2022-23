import java.util.InputMismatchException;
import java.util.Scanner;

public class FibonacciSequenceElementApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfElements = enterPositiveNumber(input);
        printNthFibonacciElement(numberOfElements);
    }

    private static int nthFibonacciElement(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return nthFibonacciElement(n - 1) + nthFibonacciElement(n - 2);
        }
    }
    private static void printNthFibonacciElement(int n) {
        System.out.println( n + " element of Fibonacci sequence is: " + nthFibonacciElement(n));
    }

    private static int enterPositiveNumber(Scanner input) {
        System.out.print("Enter the number of the element you want to see: ");
        boolean correctInput = false;
        int numberOfElements = 0;
        while (!correctInput) {
            try {
                numberOfElements = input.nextInt();
                if (numberOfElements <= 0) {
                    System.out.print("Your number must be a positive integer: ");
                } else {
                    correctInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.print("Enter a valid, positive integer: ");
                input.next();
            }
        }
        return numberOfElements;
    }
}
