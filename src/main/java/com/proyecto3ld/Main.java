package src.main.java.com.proyecto3ld;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.main.java.com.proyecto3ld.fuzzy.LingVariable;
import src.main.java.com.proyecto3ld.inference.FuzzyInference;
import src.main.java.com.proyecto3ld.inference.KnowledgeBase;
import src.main.java.com.proyecto3ld.util.FuzzyLoad;

//Clase principal que inicializa el sistema y ejecuta el proceso de inferencia
public class Main {
    public static void main(String[] args) throws IOException {
        // Cargar variables desde archivo


        System.out.println((new File("../../../resoruces/variables.txt").getCanonicalPath()));
       List<LingVariable> variables = FuzzyLoad.loadVariablesFromFile("../../../resources/variables.txt");

        // Crear base de conocimiento
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.loadRulesFromFile("../../../resources/rules.txt");
        
        // Crear motor de inferencia
        FuzzyInference inference = new FuzzyInference(knowledgeBase);
        
        // Asociar variables de entrada y salida
        for (LingVariable variable : variables) {
            if (variable.getName().equals("Tip")) {
                inference.setOutputVariable(variable);
            } else {
                inference.addInputVariable(variable);
            }
        }
        
        // Prueba de inferencia con diferentes entradas
        Map<String, Double> inputValues = new HashMap<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el tiempo de espera: ");
        double waitTime = in.nextDouble();
        System.out.println("Ingrese la calidad del servicio: ");
        double quality = in.nextDouble();
        inputValues.put("WaitTime", waitTime); // Valores de prueba
        inputValues.put("Quality", quality);
        
        double output = inference.infer(inputValues);
        System.out.println("Resultado de inferencia (Tip): " + output);
    }
}
