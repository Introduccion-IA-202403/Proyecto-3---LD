package src.main.java.com.proyecto3ld.fuzzy;
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
    
    // Constructor para función triangular (tres parámetros)
    public FuzzySet(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = b; // En funciones triangulares, d es igual a b.
    }
    
    // Constructor para función trapezoidal (cuatro parámetros)
    public FuzzySet(String name, double a, double b, double c, double d) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    // Método para calcular el grado de pertenencia basado en los valores
    public double calculateMembership(double x) {
        // Verificar si x está dentro del rango del conjunto difuso
        if (x < a || x > d) {
            membershipValue = 0.0;
            return membershipValue;
        } else if (x >= b && x <= c) {
            membershipValue = 1.0;
            return membershipValue;
        } else if (x >= a && x < b) {
            membershipValue = (x - a) / (b - a);
            return membershipValue;
        } else if (x > c && x <= d) {
            membershipValue = (d - x) / (d - c);
            return membershipValue;
        } else {
            membershipValue = 0.0;
            return membershipValue;
        }
    }
    public double getMembershipValue() {
        return membershipValue;
    }
    
    public String getName() {
        return name;
    }
}
