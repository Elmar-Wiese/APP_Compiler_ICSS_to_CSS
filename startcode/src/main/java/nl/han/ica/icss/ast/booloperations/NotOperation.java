package nl.han.ica.icss.ast.booloperations;


import nl.han.ica.icss.ast.BooleanExpression;

public class NotOperation extends BooleanExpression {
    @Override
    public String getNodeLabel() {
        return "Not";
    }
}
