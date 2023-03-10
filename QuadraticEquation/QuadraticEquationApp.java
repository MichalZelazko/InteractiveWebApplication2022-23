import java.util.InputMismatchException;
import java.util.Scanner;

public class QuadraticEquationApp {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            double a = enterParameter(input, "a");
            double b = enterParameter(input, "b");
            double c = enterParameter(input, "c");
            QuadraticEquation equation = new QuadraticEquation(a, b, c);
            equation.printRoots();
        } catch (NoRootsException e) {
            System.out.println(e.getMessage());
        }
    }
    private static double enterParameter(Scanner input, String parameter) {
        System.out.print("Enter the parameter " + parameter + ": ");
        boolean correctInput = false;
        double parameterValue = 0;
        while(!correctInput){
            try{
                parameterValue = input.nextDouble();
                correctInput = true;
            } catch (InputMismatchException e) {
                System.out.print("Enter a valid number: ");
                input.next();
            }
        }
        return parameterValue;
    }
}