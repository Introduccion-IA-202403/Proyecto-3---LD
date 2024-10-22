/**
 * Esta clase representa un conjunto difuso, con una función de pertenencia
 * triangular o trapezoidal. Incluye métodos para definir y calcular la pertenencia.
 */
public class FuzzySet {
    private String name;
    double a; // Parámetros para las funciones de pertenencia.
    private double b;
    private double c;
    double d;
    
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
        if (x < a || x > d) return 0.0;
        if (x >= b && x <= c) return 1.0;
        if (x >= a && x < b) return (x - a) / (b - a);
        if (x > c && x <= d) return (d - x) / (d - c);
        return 0.0;
    }
    
    public String getName() {
        return name;
    }
}
