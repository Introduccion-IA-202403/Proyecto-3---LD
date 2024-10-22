package src.main.java.com.proyecto3ld.inference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.com.proyecto3ld.fuzzy.FuzzySet;
import src.main.java.com.proyecto3ld.fuzzy.LingVariable;

//Esta clase representa el motor de inferencia difusa que procesa reglas y genera conclusiones
public class FuzzyInference {
    private KnowledgeBase knowledgeBase;
    private Map<String, LingVariable> inputVariables;
    private LingVariable outputVariable;

    public FuzzyInference(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
        this.inputVariables = new HashMap<>();
    }
    
    // Método para agregar variables de entrada
    public void addInputVariable(LingVariable variable) {
        inputVariables.put(variable.getName(), variable);
    }

    // Método para establecer la variable de salida
    public void setOutputVariable(LingVariable variable) {
        this.outputVariable = variable;
    }
    
    // Realiza la inferencia difusa tipo Mamdani basándose en reglas cargadas.
    public double infer(Map<String, Double> inputValues) {
        Map<String, Double> ruleResults = new HashMap<>();
        
        // Fuzzificación: Determinar el grado de pertenencia de las entradas
        inputVariables.forEach((name, variable) -> {
            double inputValue = inputValues.get(name);
            variable.fuzzify(inputValue);
        });
        
        // Evaluación de reglas
        for (FuzzyRule rule : knowledgeBase.getRules()) {
            double degree1 = getDegreeOfMembership(inputVariables.get(rule.getInput1Name()), rule.getInput1Set());
            double degree2 = getDegreeOfMembership(inputVariables.get(rule.getInput2Name()), rule.getInput2Set());
            double ruleStrength = Math.min(degree1, degree2); // Operador AND (mínimo)
            
            // Agregar a resultados agregados para cada conjunto difuso de salida
            ruleResults.merge(rule.getOutputSet(), ruleStrength, Math::max);
        }
        
        // Defuzzificación
        return defuzzify(ruleResults);
    }

    // Obtener el grado de pertenencia de un conjunto difuso específico
    private double getDegreeOfMembership(LingVariable variable, String fuzzySetName) {
        for (FuzzySet set : variable.getFuzzySets()) {
            if (set.getName().equals(fuzzySetName)) {
                return set.calculateMembership(0); // Aquí se obtendría el valor calculado durante la fuzzificación
            }
        }
        return 0.0;
    }
    
    // Método para realizar defuzzificación (ej. Centroide)
    private double defuzzify(Map<String, Double> ruleResults) {
        double numerator = 0.0;
        double denominator = 0.0;
        
        for (FuzzySet set : outputVariable.getFuzzySets()) {
            double membership = ruleResults.getOrDefault(set.getName(), 0.0);
            double representativeValue = (set.a + set.d) / 2; // Centro aproximado del conjunto difuso
            numerator += representativeValue * membership;
            denominator += membership;
        }
        
        return (denominator == 0) ? 0.0 : (numerator / denominator);
    }
}
