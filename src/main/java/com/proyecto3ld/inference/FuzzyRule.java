package src.main.java.com.proyecto3ld.inference;
import java.util.List;

import src.main.java.com.proyecto3ld.fuzzy.FuzzyCondition;

//Esta clase representa una regla difusa, que conecta entradas lingüísticas con una salida.

public class FuzzyRule {
    private String input1Name;
    private String input1Set;
    private String input2Name;
    private String input2Set;
    private String outputName;
    private String outputSet;
    
    //Constructor de la regla que define entradas y salidas
    public FuzzyRule(String input1Name, String input1Set, String input2Name, String input2Set, String outputName, String outputSet) {
        this.input1Name = input1Name;
        this.input1Set = input1Set;
        this.input2Name = input2Name;
        this.input2Set = input2Set;
        this.outputName = outputName;
        this.outputSet = outputSet;
    }
    
    public FuzzyRule(List<FuzzyCondition> ruleConditions, FuzzyCondition fuzzyCondition) {

    }

    // Método para mostrar la regla en un formato legible
    @Override
    public String toString() {
        return "IF " + input1Name + " IS " + input1Set + " AND " + input2Name + " IS " + input2Set +
               " THEN " + outputName + " IS " + outputSet;
    }

    // Métodos Getters para poder acceder a las propiedades de la regla
    public String getInput1Name() {
        return input1Name;
    }

    public String getInput1Set() {
        return input1Set;
    }

    public String getInput2Name() {
        return input2Name;
    }

    public String getInput2Set() {
        return input2Set;
    }

    public String getOutputName() {
        return outputName;
    }

    public String getOutputSet() {
        return outputSet;
    }
}