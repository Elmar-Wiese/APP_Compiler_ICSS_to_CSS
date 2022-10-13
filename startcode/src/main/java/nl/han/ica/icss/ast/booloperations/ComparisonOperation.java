package nl.han.ica.icss.ast.booloperations;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.BooleanExpression;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.literals.*;

import java.util.ArrayList;

public abstract class ComparisonOperation extends BooleanExpression {
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

    public <T extends Literal> BoolLiteral operation(T val1, T val2) {
        boolean newVal = false;

        if (val1 instanceof BoolLiteral)
            newVal = compare(((BoolLiteral) val1).value, ((BoolLiteral) val2).value);
        else if (val1 instanceof NumberLiteral)
            newVal = compare(((NumberLiteral) val1).getNumber(), ((NumberLiteral) val2).getNumber());

//        if (val1 instanceof PercentageLiteral | val2 instanceof PercentageLiteral) {
//            newVal = new PercentageLiteral(calculation(val1.getNumber(), val2.getNumber()));
//        } else if (val1 instanceof PixelLiteral | val2 instanceof PixelLiteral) {
//            newVal = new PixelLiteral(calculation(val1.getNumber(), val2.getNumber()));
//        } else {
//            newVal = new ScalarLiteral(calculation(val1.getNumber(), val2.getNumber()));
//        }

        return new BoolLiteral(newVal);
    }

    abstract protected boolean compare(Comparable val1, Comparable val2);
}
