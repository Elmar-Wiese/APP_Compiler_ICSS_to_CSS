package nl.han.ica.icss.ast.booloperations;

import nl.han.ica.icss.ast.literals.BoolLiteral;

public class AndOperation extends ComparisonOperation{
    @Override
    public String getNodeLabel() {
        return "AND";
    }

    @Override
    protected boolean compare(Comparable val1, Comparable val2) {
        return (boolean) val1 && (boolean) val2;
    }
}
