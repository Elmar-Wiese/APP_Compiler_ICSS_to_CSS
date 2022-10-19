package nl.han.ica.icss.ast.booloperations;

public class EqualsOperation extends ComparisonOperation{
    @Override
    public String getNodeLabel() {
        return "Equals";
    }
    @Override
    protected boolean compare(Comparable val1, Comparable val2) {
        return val1.equals(val2);
    }
}
