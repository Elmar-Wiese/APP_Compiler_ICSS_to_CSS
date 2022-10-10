package nl.han.ica.icss.ast;

import nl.han.ica.icss.ast.literals.NumberLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

import java.util.ArrayList;

public abstract class Operation extends Expression {

    public Expression lhs;
    public Expression rhs;

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if(lhs != null)
            children.add(lhs);
        if(rhs != null)
            children.add(rhs);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if(lhs == null) {
            lhs = (Expression) child;
        } else if(rhs == null) {
            rhs = (Expression) child;
        }
        return this;
    }

    public NumberLiteral operation(NumberLiteral val1, NumberLiteral val2) {
        NumberLiteral newVal;

        if (val1 instanceof PercentageLiteral | val2 instanceof PercentageLiteral) {
            newVal = new PercentageLiteral(calculation(val1.getNumber(), val2.getNumber()));
        } else if (val1 instanceof PixelLiteral | val2 instanceof PixelLiteral) {
            newVal = new PixelLiteral(calculation(val1.getNumber(), val2.getNumber()));
        } else {
            newVal = new ScalarLiteral(calculation(val1.getNumber(), val2.getNumber()));
        }

        return newVal;
    }

    abstract public int calculation(int val1, int val2);


}
