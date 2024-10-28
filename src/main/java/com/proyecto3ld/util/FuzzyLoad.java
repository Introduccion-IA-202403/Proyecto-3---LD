package src.main.java.com.proyecto3ld.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.proyecto3ld.fuzzy.FuzzySet;
import src.main.java.com.proyecto3ld.fuzzy.LingVariable;

public class FuzzyLoad {

    /**
     * Carga las variables lingüísticas desde un archivo de texto
     * @param filePath
     * @return
     */
    public static List<LingVariable> loadVariablesFromFile(String filePath) {

        // Declarar una lista de variables lingüísticas
        List<LingVariable> variables = new ArrayList<>();

        // Leer el archivo de texto
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            LingVariable currentVariable = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                if (!line.contains(" ")) {
                    // Nueva variable lingüística
                    if (currentVariable != null) {
                        // Agregar variable a la lista ya que se encontró una nueva
                        variables.add(currentVariable);
                    }
                    // Crear nueva variable lingüística
                    currentVariable = new LingVariable(line);
                } else if (currentVariable != null) {
                    // Agregar conjuntos difusos a la variable
                    String[] parts = line.split(" ");
                    String setName = parts[0];
                    double a = Double.parseDouble(parts[1]);
                    double b = Double.parseDouble(parts[2]);
                    double c = Double.parseDouble(parts[3]);
                    FuzzySet set = new FuzzySet(setName, a, b, c);
                    currentVariable.addFuzzySet(set);
                }
            }
            if (currentVariable != null) {
                // Agregar la última variable a la lista
                variables.add(currentVariable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variables;
    }
}
