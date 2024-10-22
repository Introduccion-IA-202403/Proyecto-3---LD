import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuzzyLoad {
    public static List<LingVariable> loadVariablesFromFile(String filePath) {
        List<LingVariable> variables = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            LingVariable currentVariable = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (!line.contains(" ")) {
                    // Nueva variable lingüística
                    if (currentVariable != null) {
                        variables.add(currentVariable);
                    }
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
                variables.add(currentVariable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variables;
    }
}
