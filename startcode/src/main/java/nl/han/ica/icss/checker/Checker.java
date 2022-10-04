package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;



public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        // variableTypes = new HANLinkedList<>();
        // variable LinkedList with Hashmap for scope.

        checkNodes(ast.root);
    }

    private void checkNodes (ASTNode node) {
        for (ASTNode childNode:  node.getChildren() ) {
            checkNode(childNode);
            checkNodes(childNode);
        }
    }

    private void checkNode(ASTNode childNode) {

        checkCH04(childNode);

    }
    // 'color' | 'background-color'
    // ColorLiteral
    // | 'width' | 'height'
    // PercantageLiteral, PixelLiteral
    private void checkCH04(ASTNode node) {
        if (!(node instanceof Declaration)) {
            return;
        }
        Declaration declaration = ((Declaration) node);
        // TODO herschrijven naar minder dubbele code.
        if (declaration.property.name.equals("color")  || declaration.property.name.equals("background-color")) {
            if (declaration.expression instanceof Literal) {
                Literal literal = (Literal) declaration.expression;
                if (!(literal instanceof ColorLiteral)) {
                    declaration.setError("color declarations must be assigned hexadecimal values");
                }
            }
        }
        if (declaration.property.name.equals("width") || declaration.property.name.equals("height")) {
            if (declaration.expression instanceof Literal) {
                Literal literal = (Literal) declaration.expression;
                if (!(literal instanceof PercentageLiteral || literal instanceof PixelLiteral)) {
                    declaration.setError("size declarations must be assigned percentage or pixel values");
                }
            }
        }
    }

}
