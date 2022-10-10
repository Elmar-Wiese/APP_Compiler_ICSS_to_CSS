package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.datastructures.StackImpl;
import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.NumberLiteral;
import nl.han.ica.icss.ast.operations.MultiplyOperation;

public class Evaluator implements Transform {

    //private IHANLinkedList<HashMap<String, Literal>> variableValues;

    SymbolTableImpl<String, Literal> symbolTable;


    public Evaluator() {
        symbolTable = new SymbolTableImpl<>();
    }

    @Override
    public void apply(AST ast) {
        //variableValues = new HANLinkedList<>();
        evaluateNodes(ast.root);
    }

    private void evaluateNodes(ASTNode node) {
        for (ASTNode childNode : node.getChildren()) {
            constructsymboltable(childNode);

            evaluateNode(childNode, node);
            evaluateNodes(childNode);

            aftersymboltable(childNode);
        }
    }

    private void evaluateNode(ASTNode childNode, ASTNode parent) {

    }

    private void aftersymboltable(ASTNode childNode) {
        if (childNode instanceof Stylerule) {
            symbolTable.removeScope();
        }
    }

    private void constructsymboltable(ASTNode childNode) {
        if (childNode instanceof Stylerule) {
            symbolTable.newScope();
        }
    }

    IHANStack<NumberLiteral> calcStack;

    // BOTTUM UP L R current
    private void evaluateOperations(ASTNode node, ASTNode parent) {
        if(node instanceof Operation && !(parent instanceof Operation)) {
            calcStack = new StackImpl<>();
        }
    }

    private void LRcurrentCalculation(Expression expression) {
        if (expression instanceof Operation) {
            LRcurrentCalculation(((Operation)expression).lhs);

            LRcurrentCalculation(((Operation)expression).rhs);

            doOperation((Operation) expression);
        }
        if (expression instanceof NumberLiteral) {
            calcStack.push((NumberLiteral) expression);
        }
        if (expression instanceof VariableReference) {
            calcStack.push((NumberLiteral) symbolTable.getValue(((VariableReference) expression).name));
        }
    }

    private void doOperation(Operation op) {
        if (op instanceof MultiplyOperation) {
            NumberLiteral val1 = calcStack.pop();
            NumberLiteral val2 = calcStack.pop();



            calcStack.push(wrapCalculation(val1.getNumber() * val2.getNumber()));
        }

    }

    private <type> NumberLiteral wrapCalculation(int integer) {

        return null;
    }

//    private int unwrapLiteral(Literal l) {
//        if (l instanceof ) {
//
//        }
//    }

}