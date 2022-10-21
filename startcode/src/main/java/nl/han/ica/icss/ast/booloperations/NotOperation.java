package nl.han.ica.icss.ast.booloperations;


import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.BooleanExpression;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.literals.BoolLiteral;

import java.util.ArrayList;
// Dit heet unary handig voor een volgende keer.
public class NotOperation extends BooleanExpression {
    public Expression value;

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if(value != null)
            children.add(value);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if(value == null) {
            value = (Expression) child;
        }
        return this;
    }

    @Override
    public String getNodeLabel() {
        return "Not";
    }


    public boolean run(BoolLiteral bool) {
        return bool.value = !bool.value;
    }

}
