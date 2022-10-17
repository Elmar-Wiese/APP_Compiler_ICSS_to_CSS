package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;

import java.util.LinkedList;
import java.util.List;

public class Evaluator implements Transform {

    SymbolTableImpl<String, Literal> symbolTable;

    SymbolTableImpl<String, Literal> getSymbolTable() { return symbolTable; }

    private List<BaseTransform> transforms;

    public Evaluator() {
        transforms = new LinkedList<>();
        transforms.add(new IfElseEvaluator(this));
        transforms.add(new VariableReferenceTransform(this));
        transforms.add(new OperationTransform(this));
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
            if (var.expression instanceof Operation) {
                evaluateNode(var.expression, var);
                symbolTable.assignSymbol(var.name.name, (Literal) var.expression);
            } else if (var.expression instanceof VariableReference) {
                symbolTable.assignSymbol(var.name.name, symbolTable.getValue(var.name.name));
            } else { // Literal
                symbolTable.assignSymbol(var.name.name, (Literal) var.expression);
            }
        }
    }



}