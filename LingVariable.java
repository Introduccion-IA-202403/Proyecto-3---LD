import java.util.ArrayList;
import java.util.List;

//Esta clase representa una variable lingüística que contiene múltiples conjuntos difusos
public class LingVariable {
    private String name;
    private List<FuzzySet> fuzzySets;
    
    public LingVariable(String name) {
        this.name = name;
        this.fuzzySets = new ArrayList<>();
    }
    
    // Método para agregar un conjunto difuso a la variable lingüística
    public void addFuzzySet(FuzzySet fuzzySet) {
        fuzzySets.add(fuzzySet);
    }
    
    // Método para calcular los grados de pertenencia de todos los conjuntos para un valor real (fuzzificación)
    public void fuzzify(double value) {
        System.out.println("Fuzificación para la variable: " + name);
        for (FuzzySet set : fuzzySets) {
            double membership = set.calculateMembership(value);
            System.out.println("Grado de pertenencia en " + set.getName() + ": " + membership);
        }
    }
    
    public String getName() {
        return name;
    }
    
    public List<FuzzySet> getFuzzySets() {
        return fuzzySets;
    }
}
