package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.datastructures.StackImpl;
import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.NumberLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.operations.MultiplyOperation;

import java.util.List;
import java.util.Optional;

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
        evaluateIfElse(childNode, parent);
        evaluateVariableReference(childNode, parent);
        evaluateOperations(childNode, parent);

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
            //symbolTable.assignSymbol(var.name.name, var.expression);
            if (var.expression instanceof Operation) {
                //LRcurrentCalculation(var.expression);
                //symbolTable.assignSymbol(var.name.name, LRcurrentCalculation(var.expression));
                evaluateOperations(var.expression, childNode);
                symbolTable.assignSymbol(var.name.name, (Literal) var.expression);
            } else if (var.expression instanceof VariableReference) {
                symbolTable.assignSymbol(var.name.name, symbolTable.getValue(var.name.name));
            } else { // Literal
                symbolTable.assignSymbol(var.name.name, (Literal) var.expression);
            }
        }
    }

    IHANStack<NumberLiteral> calcStack;

    // BOTTUM UP L R current
    private void evaluateOperations(ASTNode node, ASTNode parent) {
        if(node instanceof Operation && !(parent instanceof Operation)) {
            calcStack = new StackImpl<>();

            replaceNode(parent, node, LRcurrentCalculation(((Operation) node)));
        }
    }

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
            calcStack.push((NumberLiteral) symbolTable.getValue(((VariableReference) expression).name));
        }

        return calcStack.peek();
    }

    private void doOperation(Operation op) {
        NumberLiteral val1 = calcStack.pop();
        NumberLiteral val2 = calcStack.pop();
        calcStack.push(op.operation(val1, val2));
    }

    // Single child
    private void replaceNode(ASTNode parent, ASTNode oldNode ,ASTNode newNode) {
        parent.removeChild(oldNode);
        parent.addChild(newNode);
    }

    // Like for bodies of stylerules and if statements.
    private void replaceNodeWithNodes(ASTNode parent, ASTNode oldNode, List<ASTNode> replacement) {
        parent.removeChild(oldNode);
        for (ASTNode replace: replacement) {
            parent.addChild(replace);
        }
    }

    private void evaluateVariableReference(ASTNode childNode, ASTNode parent) {
        if (childNode instanceof VariableReference && parent instanceof Declaration) {
            VariableReference var = (VariableReference) childNode;
            replaceNode(parent, childNode, symbolTable.getValue(var.name));
        }
    }

    private void evaluateIfElse(ASTNode childNode, ASTNode parent) {
        if(!(childNode instanceof IfClause))
            return;

        IfClause ifClause = (IfClause) childNode;
        BoolLiteral condition = null;
        if (ifClause.conditionalExpression instanceof Literal) {
            condition = (BoolLiteral) ifClause.conditionalExpression;
        } else if (ifClause.conditionalExpression instanceof VariableReference) {
            condition = (BoolLiteral) symbolTable.getValue(((VariableReference)ifClause.conditionalExpression).name);
        }

        if (condition.value) {
            evaluateNodes(childNode);
            replaceNodeWithNodes(parent, childNode, ifClause.body);
        } else {
            Optional<ElseClause> optionalElseClause = Optional.of(ifClause.elseClause);
            if(optionalElseClause.isPresent()) {
                evaluateNodes(childNode);
                replaceNodeWithNodes(parent, childNode, optionalElseClause.get().body);
            }
        }
    }
}