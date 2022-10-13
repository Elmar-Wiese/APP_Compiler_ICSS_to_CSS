package nl.han.ica.icss.ast.booloperations;

public class LessThanOperation extends ComparisonOperation{
    @Override
    public String getNodeLabel() {
        return "Less Than";
    }

    @Override
    protected boolean compare(Comparable val1, Comparable val2) {
        //int comparison = val1.compareTo(val2);
        return val1.compareTo(val2) < 0;
    }
}
