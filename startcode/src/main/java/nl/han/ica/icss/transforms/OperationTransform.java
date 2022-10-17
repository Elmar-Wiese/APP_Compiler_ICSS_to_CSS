package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.datastructures.StackImpl;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.literals.NumberLiteral;

public class OperationTransform extends BaseTransform{
    IHANStack<NumberLiteral> calcStack;

    public OperationTransform(Evaluator ev) {
        super(ev);
    }

    @Override
    public void transform(ASTNode childNode, ASTNode parent) {
        if(childNode instanceof Operation && !(parent instanceof Operation)) {
            calcStack = new StackImpl<>();

            replaceNode(parent, childNode, LRcurrentCalculation(((Operation) childNode)));
        }
    }
    // BOTTUM UP    L R current
    private NumberLiteral LRcurrentCalculation(Expression expression) {
        if (expression instanceof Operation) {
            LRcurrentCalculation(((Operation)expression).lhs);

            LRcurrentCalculation(((Operation)expression).rhs);

            doOperation((Operation) expression);
        }
        if (expression instanceof NumberLiteral) {
            calcStack.push((NumberLiteral) expression);
        }
        if (expression instanceof VariableReference) {
            calcStack.push((NumberLiteral) ev.getSymbolTable().getValue(((VariableReference) expression).name));
        }

        return calcStack.peek();
    }

    private void doOperation(Operation op) {
        NumberLiteral right = calcStack.pop();
        NumberLiteral left = calcStack.pop();
        calcStack.push(op.operation(left, right));
    }
}
