package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;


public class Checker {

    //private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;
    SymbolTableImpl<String, Expression> symbolTable = new SymbolTableImpl();

    public void check(AST ast) {
        // variableTypes = new HANLinkedList<>();
        // variable LinkedList with Hashmap for scope.

        checkNodes(ast.root);

    }

    private void checkNodes (ASTNode node) {
//        if(node instanceof Stylerule) {
//            symbolTable.newScope();
//        }

        for (ASTNode childNode:  node.getChildren() ) {
            constructsymboltable(childNode);

            checkNode(childNode);
            checkNodes(childNode);

            aftersymboltable(childNode);
        }
       // System.out.println(scope);
       // System.out.println(symbolTable);
    }

    //IHANLinkedList<String> scope = new LinkedListImpl<>();

    private void aftersymboltable(ASTNode childNode) {
        if(childNode instanceof Stylerule) {
            symbolTable.removeScope();
        }
    }

    private void constructsymboltable(ASTNode childNode) {
        if(childNode instanceof Stylerule) {
            symbolTable.newScope();
        }

//        if(childNode instanceof Declaration) {// Later ook if en else
//            //scope.insert(scope.getSize(), ((Stylerule)childNode).selectors.get(0).toString());
//            //new Declaration("background-color")  .addChild(new ColorLiteral("#ffffff"))
//            Declaration dec = (Declaration) childNode;
//            if (dec.expression instanceof VariableReference || (dec.expression instanceof Operation && checkExpressionVariableReference(dec.expression))) {
//                symbolTable.assignSymbol(dec.property.name, dec.expression);
//            }
//        }
        if(childNode instanceof VariableAssignment) {
            VariableAssignment var = (VariableAssignment) childNode;
            symbolTable.assignSymbol(var.name.name, var.expression);
        }
    }

    private boolean checkExpressionVariableReference(Expression ex) {
        //return ex.getChildren().stream().anyMatch(astNode -> astNode instanceof VariableReference || ast.)
        if (ex instanceof VariableReference)
            return true;
        if (ex instanceof Operation)
            return ex.getChildren().stream().anyMatch(astNode ->  checkExpressionVariableReference((Expression) astNode)); //checkExpressionVariableReference();
        return false;
    }

    private void checkNode(ASTNode childNode) {
        checkCH01CH06(childNode);
        checkCH04(childNode);
        checkCH05(childNode);
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
    // Alleen errors geven bij variable references in expressions
    private void checkCH01CH06(ASTNode node) {
        if (node instanceof VariableAssignment ) {
            checkCHO1internal(((VariableAssignment) node).expression);
        } if (node instanceof Declaration){
            checkCHO1internal(((Declaration) node).expression);
        } if (node instanceof Operation) {
            Operation op = (Operation) node;
            checkCHO1internal(op.lhs);
            checkCHO1internal(op.rhs);
        } if (node instanceof IfClause) {
            checkCHO1internal(((IfClause) node).conditionalExpression);
        }
//        if (node instanceof VariableReference) {
//            VariableReference ref = (VariableReference) node;
//            ref.
//            if (symbolTable.getValue(ref.name) == null) {
//                node.setError("Variable has not been defined");
//            }
//        }
    }

    private void checkCHO1internal(Expression expression) {
        if (expression instanceof VariableReference) {
            VariableReference ref = (VariableReference) expression;
            if(symbolTable.getValue(ref.name) == null)
                ref.setError("Variable has not been defined");
        }
    }

    //	Controleer of de conditie bij een if-statement van het type boolean is (zowel bij een variabele-referentie als een boolean literal)
    private void checkCH05(ASTNode node) {
        if(!(node instanceof IfClause))
            return;

        IfClause ifnode = (IfClause) node;
        boolean correct = false;
        if (ifnode.conditionalExpression instanceof BoolLiteral) {
            correct = true;
        }
        if(ifnode.conditionalExpression instanceof VariableReference) {
            if ( symbolTable.getValue(((VariableReference) ifnode.conditionalExpression).name ) instanceof BoolLiteral) {
                correct = true;
            }
        }

        if(!correct) {
            ifnode.setError("If statement condition is not boolean");
        }
    }

}
