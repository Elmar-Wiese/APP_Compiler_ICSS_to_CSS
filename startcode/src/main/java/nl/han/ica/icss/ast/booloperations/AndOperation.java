package nl.han.ica.icss.ast.booloperations;

public class AndOperation extends ComparisonOperation{
    @Override
    public String getNodeLabel() {
        return "AND";
    }

    @Override
    protected boolean compare(Comparable val1, Comparable val2) {
        return false;
    }
}
