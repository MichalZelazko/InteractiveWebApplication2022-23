package pl.dmcs.iwamzelazko.model;

import java.util.ArrayList;
import javax.validation.constraints.Digits;
public class QuadraticEquation {

    @Digits(message = "Enter a valid digit, <up to 5 digits>.<up to 2 digits> expected", integer = 5, fraction = 2)
    private double a;
    @Digits(message = "Enter a valid digit, <up to 5 digits>.<up to 2 digits> expected", integer = 5, fraction = 2)
    private double b;
    @Digits(message = "Enter a valid digit, <up to 5 digits>.<up to 2 digits> expected", integer = 5, fraction = 2)
    private double c;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    private double getDiscriminant() {
        return (b * b) - (4 * a * c);
    }

    private ArrayList<Double> getRoots() throws IllegalArgumentException {
        ArrayList<Double> roots = new ArrayList<>();
        if (a == 0) {
            roots.add(linearEquationCase());
        } else
            roots = quadraticEquationCase();
        return roots;
    }

    private Double linearEquationCase() throws IllegalArgumentException{
        if (b == 0)
            if (c == 0){
                throw new IllegalArgumentException("The equation is constant and equals 0. It has infinite roots.");
            } else {
                throw new IllegalArgumentException("The equation is constant. It has no roots.");
            }
        return -c / b;
    }

    private ArrayList<Double> quadraticEquationCase() throws IllegalArgumentException{
        ArrayList<Double> roots = new ArrayList<>();
        if (getDiscriminant() < 0) {
            throw new IllegalArgumentException("The equation has no real roots.");
        }
        else if (getDiscriminant() == 0) {
            roots.add(-b / (2 * a));
        }
        else {
            roots.add((-b + Math.sqrt(getDiscriminant())) / (2 * a));
            roots.add((-b - Math.sqrt(getDiscriminant())) / (2 * a));
        }
        return roots;
    }

    public void printRoots() throws IllegalArgumentException{
        ArrayList<Double> roots = this.getRoots();
        if (roots.size() == 1) {
            System.out.println("The equation has one root: " + roots.get(0));
        }
        else {
            System.out.println("The equation has two roots: " + roots.get(0) + " and " + roots.get(1));
        }
    }
}
