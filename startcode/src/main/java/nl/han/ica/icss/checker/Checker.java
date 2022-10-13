package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.Optional;


public class Checker {

    //private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;
    //SymbolTableImpl<String, Expression> symbolTable = new SymbolTableImpl();
    private SymbolTableImpl<String, ExpressionType> symbolTable = new SymbolTableImpl();

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
        if(childNode instanceof Stylerule || childNode instanceof IfClause || childNode instanceof ElseClause) {
            symbolTable.removeScope();
        }
    }

    private void constructsymboltable(ASTNode childNode) {
        if(childNode instanceof Stylerule || childNode instanceof IfClause) {
            symbolTable.newScope();
        }

        if(childNode instanceof ElseClause) {
            symbolTable.removeScope();
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
            //symbolTable.assignSymbol(var.name.name, var.expression);
            symbolTable.assignSymbol(var.name.name, resolve_type_of_lit_op_varreference(var.expression));

        }
    }

    private ExpressionType resolve_type_of_lit_op_varreference(Expression ex) {
        // Literal direct
        if (ex instanceof Literal) {
            return literalToType((Literal) ex);
        }
        // Expression check soort expression
        else if (ex instanceof Operation) {
            Operation op = (Operation) ex;
            ExpressionType type;

            type = resolve_type_of_lit_op_varreference(op.lhs);

            ExpressionType typerhs = resolve_type_of_lit_op_varreference(op.rhs);

            if (typerhs == ExpressionType.PERCENTAGE) {
                type = ExpressionType.PERCENTAGE;
            } else if (typerhs == ExpressionType.PIXEL) {
                type = ExpressionType.PIXEL;
            }

            return type;
        }
        // Var pak uit symbol table
        else if (ex instanceof VariableReference) {
            return symbolTable.getValue(((VariableReference) ex).name);
        }
        return ExpressionType.UNDEFINED;
    }


    private ExpressionType literalToType(Literal l) {
        switch (l.getClass().getName()) {
            case "nl.han.ica.icss.ast.literals.BoolLiteral":
                return ExpressionType.BOOL;
            case "nl.han.ica.icss.ast.literals.ColorLiteral":
                return ExpressionType.COLOR;
            case "nl.han.ica.icss.ast.literals.PercentageLiteral":
                return ExpressionType.PERCENTAGE;
            case "nl.han.ica.icss.ast.literals.PixelLiteral":
                return ExpressionType.PIXEL;
            case "nl.han.ica.icss.ast.literals.ScalarLiteral":
                return ExpressionType.SCALAR;
            default:
                return ExpressionType.UNDEFINED;
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
        checkCH04(childNode); // Kan CH02 en CH04 samenvoegen.
        checkCH02(childNode);
        checkCH05(childNode);
        checkCH03(childNode);
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

        ExpressionType expressionType = resolve_type_of_lit_op_varreference(declaration.expression);
        if (declaration.property.name.equals("color")  || declaration.property.name.equals("background-color")) {
            if (expressionType != ExpressionType.COLOR)
                declaration.setError("color declarations must be assigned hexadecimal values");
        }
        if (declaration.property.name.equals("width") || declaration.property.name.equals("height")) {
            if (!(expressionType == ExpressionType.PERCENTAGE || expressionType == ExpressionType.PIXEL))
                declaration.setError("size declarations must be assigned percentage or pixel values");

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
            //if ( symbolTable.getValue(((VariableReference) ifnode.conditionalExpression).name ) instanceof BoolLiteral) {
            if ( symbolTable.getValue(((VariableReference) ifnode.conditionalExpression).name ) == ExpressionType.BOOL ) {
                correct = true;
            }
        }

        if(!correct) {
            ifnode.setError("If statement condition is not boolean");
        }
    }

    private void checkCH02(ASTNode node) {
        //resolve_type_of_lit_op_varreference
        if(!(node instanceof Operation))
            return;

        Operation op = (Operation) node;

        // plus en min operanden gelijk
        if (op instanceof AddOperation || op instanceof SubtractOperation) {
            if (!(resolve_type_of_lit_op_varreference(op.lhs) == resolve_type_of_lit_op_varreference(op.rhs)))
                op.setError("Add operation values must be off same type");
        } else if (op instanceof MultiplyOperation) { // 1 scalaire waarde
            if (!(resolve_type_of_lit_op_varreference(op.lhs) == ExpressionType.SCALAR || resolve_type_of_lit_op_varreference(op.rhs) == ExpressionType.SCALAR))
                op.setError("One of the values of a multiply operation must be scalar");
        }
    }
    // Geen kleuren operaties
    private void checkCH03(ASTNode node) {
        if(!(node instanceof Operation))
            return;

        Operation op = (Operation) node;

        for (ASTNode ex: op.getChildren()) {
            if (!(ex instanceof Operation)) {
                ExpressionType type = resolve_type_of_lit_op_varreference((Expression) ex);
                if(type == ExpressionType.COLOR)
                    node.setError("Don't use colours in operations");
                if(type == ExpressionType.BOOL)
                    node.setError("Don't use booleans in operations");
            }
        }
    }
}
