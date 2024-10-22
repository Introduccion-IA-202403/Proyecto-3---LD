package src.main.java.com.proyecto3ld;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.com.proyecto3ld.fuzzy.LingVariable;
import src.main.java.com.proyecto3ld.inference.FuzzyInference;
import src.main.java.com.proyecto3ld.inference.KnowledgeBase;
import src.main.java.com.proyecto3ld.util.FuzzyLoad;

//Clase principal que inicializa el sistema y ejecuta el proceso de inferencia
public class Main {
    public static void main(String[] args) {
        // Cargar variables desde archivo
        List<LingVariable> variables = FuzzyLoad.loadVariablesFromFile("variables.txt");

        // Crear base de conocimiento
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.loadRulesFromFile("rules.txt");
        
        // Crear motor de inferencia
        FuzzyInference inference = new FuzzyInference(knowledgeBase);
        
        // Asociar variables de entrada y salida
        for (LingVariable variable : variables) {
            if (variable.getName().equals("FanSpeed")) {
                inference.setOutputVariable(variable);
            } else {
                inference.addInputVariable(variable);
            }
        }
        
        // Prueba de inferencia con diferentes entradas
        Map<String, Double> inputValues = new HashMap<>();
        inputValues.put("Temperature", 20.0); // Valores de prueba
        inputValues.put("Humidity", 45.0);
        
        double output = inference.infer(inputValues);
        System.out.println("Resultado de inferencia (FanSpeed): " + output);
    }
}
