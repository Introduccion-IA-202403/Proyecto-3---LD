package src.main.java.com.proyecto3ld.fuzzy;

enum FuzzySetType {
    TRIANGLE,
    RECTANGLE
}
/**
 * Esta clase representa un conjunto difuso, con una función de pertenencia
 * triangular o trapezoidal. Incluye métodos para definir y calcular la pertenencia.
 */
public class FuzzySet {
    private String name;
    public double a; // Parámetros para las funciones de pertenencia.
    private double b;
    private double c;
    public double d;
    private double membershipValue; // Valor de pertenencia calculado durante la fuzzificación.
    private FuzzySetType type;


    // Constructor para función triangular (tres parámetros)
    public FuzzySet(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = c; // En funciones triangulares, d es igual a b.
        this.type = FuzzySetType.TRIANGLE;
        // System.out.println("Los limites para " + name + " son");
        // System.out.println("a -> " + a);
        // System.out.println("b -> " + b);
        // System.out.println("c -> " + c);
    }
    
    // Constructor para función trapezoidal (cuatro parámetros)
    public FuzzySet(String name, double a, double b, double c, double d) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.type = FuzzySetType.RECTANGLE;
    }
    
    // Método para calcular el grado de pertenencia basado en los valores
    public double calculateMembership(double x) {
        if (this.type == FuzzySetType.TRIANGLE) return this.calculateTriangle(x);
        else return this.calculateTrapeze(x);
    }

    public double calculateTrapeze(double x) {
        membershipValue = 0.0;
        if (x < a || x > d) membershipValue = 0.0;
        else if (x >= a && x < b) membershipValue = (x-a)/(b-a);
        else if (x >= b && x < c) membershipValue = 1.0;
        else if (x >= c && x < d) membershipValue = (d-x)/(d-c);
        return membershipValue;
    }

    public double calculateTriangle(double x) {
        membershipValue = 0.0;
        if (x < a || x > c) membershipValue = 0.0;
        else if (x >= a && x < b) membershipValue = (x-a)/(b-a);
        else if (x >= b && x <= c) membershipValue = (c-x)/(c-b);
        return membershipValue;
    }

    public double getMembershipValue() {
        return membershipValue;
    }
    
    public String getName() {
        return name;
    }
}
