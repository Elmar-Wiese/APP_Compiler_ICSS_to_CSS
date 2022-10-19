package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.booloperations.ComparisonOperation;
import nl.han.ica.icss.ast.booloperations.NotOperation;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.NumberLiteral;

import java.util.LinkedList;
import java.util.List;

public class Evaluator implements Transform {

    SymbolTableImpl<String, Literal> symbolTable;

    private List<BaseTransform> transforms;

    public Evaluator() {
        transforms = new LinkedList<>();
        transforms.add(new IfElseEvaluator(this));
        transforms.add(new DeclarationTransform(this));
    }

    @Override
    public void apply(AST ast) {
        symbolTable = new SymbolTableImpl<>();
        evaluateNodes(ast.root);
    }


    void evaluateNodes(ASTNode node) {
        for (ASTNode childNode : node.getChildren()) {
            constructsymboltable(childNode);

            evaluateNode(childNode, node);
            evaluateNodes(childNode);

            aftersymboltable(childNode);
        }
    }

    private void evaluateNode(ASTNode childNode, ASTNode parent) {
        //evaluateIfElse(childNode, parent);
        //evaluateVariableReference(childNode, parent);
        //evaluateOperations(childNode, parent);
        for (BaseTransform tf: transforms) {
            tf.transform(childNode, parent);
        }
    }

    private void aftersymboltable(ASTNode childNode) {
        if (childNode instanceof Stylerule) {
            symbolTable.removeScope();
        }
    }

    private void constructsymboltable(ASTNode childNode) {
        if (childNode instanceof Stylerule) {
            symbolTable.newScope();
        } else
        if(childNode instanceof VariableAssignment) {
            VariableAssignment var = (VariableAssignment) childNode;
            symbolTable.assignSymbol(var.name.name, expressionToLiteral(var.expression));
//            if (var.expression instanceof Operation) {
//                evaluateNode(var.expression, var);
//                symbolTable.assignSymbol(var.name.name, (Literal) var.expression);
//            } else if (var.expression instanceof VariableReference) {
//                symbolTable.assignSymbol(var.name.name, symbolTable.getValue(var.name.name));
//            } else { // Literal
//                symbolTable.assignSymbol(var.name.name, (Literal) var.expression);
//            }
        }
    }

    public Literal expressionToLiteral(Expression expression) {
        Literal returnValue = null;

        if(expression instanceof Operation) {
            returnValue = LRcurrentCalculation((Operation) expression);
        } else if (expression instanceof VariableReference) {
            returnValue = symbolTable.getValue(((VariableReference) expression).name);
        } else if (expression instanceof Literal) {
            returnValue = (Literal)expression;
        } else if (expression instanceof BooleanExpression) {
            returnValue = LRcurrentBooleanExpression((BooleanExpression) expression); // Hmm deze en die van calculation lijken op elkaar.
        }

        return returnValue;
    }


    // BOTTUM UP    L R current
    // Jemig wat een heftige recursie kosten.
    private NumberLiteral LRcurrentCalculation(Operation op) {
        NumberLiteral left = (NumberLiteral) expressionToLiteral(op.lhs);
        NumberLiteral right = (NumberLiteral) expressionToLiteral(op.rhs);
        return op.operation(left, right);
    }

    private BoolLiteral LRcurrentBooleanExpression(BooleanExpression be) {
        if (be instanceof NotOperation) {
            NotOperation no = ((NotOperation) be);
            return new BoolLiteral(no.run((BoolLiteral) expressionToLiteral(no.value)));
        } else {
            ComparisonOperation co = (ComparisonOperation) be;
            return co.operation(expressionToLiteral(co.lhs), expressionToLiteral(co.rhs));
        }
    }

//    private void doOperation(Operation op) {
////        NumberLiteral right = calcStack.pop();
////        NumberLiteral left = calcStack.pop();
////        calcStack.push(op.operation(left, right));
//    }

    //private NumberLiteral LRcurrentCalculation(Operation op) {
        //IHANStack<NumberLiteral> calcStack = new StackImpl<>();
        //calcStack.push((NumberLiteral) expressionToLiteral(op.lhs));

        //calcStack.push((NumberLiteral) expressionToLiteral(op.rhs));
//        if (expression instanceof Operation) {
//            IHANStack<NumberLiteral> calcStack;
//            calcStack.push();
//            LRcurrentCalculation(((Operation)expression).lhs);
//
//            LRcurrentCalculation(((Operation)expression).rhs);
//
//            doOperation((Operation) expression);
//        }
//        if (expression instanceof NumberLiteral) {
//            calcStack.push((NumberLiteral) expression);
//        }
//        if (expression instanceof VariableReference) {
//            calcStack.push((NumberLiteral) ev.getSymbolTable().getValue(((VariableReference) expression).name));
//        }
//
//        return calcStack.peek();
//    }
}