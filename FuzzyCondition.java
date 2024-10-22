public class FuzzyCondition {
    private String variableName;
    private String fuzzySetName;

    public FuzzyCondition(String variableName, String fuzzySetName) {
        this.variableName = variableName;
        this.fuzzySetName = fuzzySetName;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getFuzzySetName() {
        return fuzzySetName;
    }

    @Override
    public String toString() {
        return variableName + " IS " + fuzzySetName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FuzzyCondition that = (FuzzyCondition) obj;
        return variableName.equals(that.variableName) && fuzzySetName.equals(that.fuzzySetName);
    }

    @Override
    public int hashCode() {
        return variableName.hashCode() * 31 + fuzzySetName.hashCode();
    }
}

