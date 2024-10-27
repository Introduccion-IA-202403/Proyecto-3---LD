package src.main.java.com.proyecto3ld.inference;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Esta clase representa la base de conocimiento que almacena las reglas difusas.
public class KnowledgeBase {
    private List<FuzzyRule> rules = new ArrayList<>();

    // Carga reglas desde un archivo de texto y las almacena en la base de conocimiento
    public void loadRulesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // Ignorar líneas vacías

                // Verificar que la línea siga el formato correcto
                if (line.startsWith("IF") && line.contains("THEN")) {
                    String[] parts = line.split("\\s+"); // Usar \\s+ para dividir por cualquier cantidad de espacios

                    // Imprimir el contenido del arreglo para depuración
                    System.out.println("Partes: " + Arrays.toString(parts));

                    // Asegurar longitud adecuada antes de acceder a índices
                    if (parts.length < 12) { // Cambiado a 12 para cubrir el formato básico pero revisar
                        System.out.println("Formato de regla inválido (menos de 12 partes): " + line);
                        continue; // Saltar reglas mal formadas
                    }

                    // Ajustar los índices para que coincidan con las partes de la regla
                    String inputVar1 = parts[1]; // Variable de entrada 1
                    String inputSet1 = parts[3]; // Conjunto de entrada 1
                    String inputVar2 = parts[5]; // Variable de entrada 2
                    String inputSet2 = parts[7]; // Conjunto de entrada 2
                    String outputVar = parts[9]; // Variable de salida
                    String outputSet = parts[11]; // Conjunto de salida

                    // Crear una nueva regla difusa basada en las partes procesadas
                    FuzzyRule rule = new FuzzyRule(inputVar1, inputSet1, inputVar2, inputSet2, outputVar, outputSet);
                    rules.add(rule);
                } else {
                    System.out.println("Formato de regla inválido: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     

    public List<FuzzyRule> getRules() {
        return rules;
    }

    //Imprime todas las reglas cargadas en la base de conocimiento.
    public void displayRules() {
        for (FuzzyRule rule : rules) {
            System.out.println(rule);
        }
    }
}
