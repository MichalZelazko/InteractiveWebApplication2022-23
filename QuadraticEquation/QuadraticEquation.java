import java.util.ArrayList;

public class QuadraticEquation {
    private double a, b, c;
    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double getDiscriminant() {
        return (b * b) - (4 * a * c);
    }

    private ArrayList<Double> getRoots() throws NoRootsException {
        ArrayList<Double> roots = new ArrayList<>();
        if (getDiscriminant() < 0) {
            throw new NoRootsException("The equation has no real roots.");
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

    public void printRoots() throws NoRootsException{
        ArrayList<Double> roots = this.getRoots();
        if (roots.size() == 1) {
            System.out.println("The equation has one root: " + roots.get(0));
        }
        else {
            System.out.println("The equation has two roots: " + roots.get(0) + " and " + roots.get(1));
        }
    }
}
