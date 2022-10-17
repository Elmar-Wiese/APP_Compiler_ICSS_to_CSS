package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;
import java.util.LinkedList;
import java.util.List;

public class Checker {
    private SymbolTableImpl<String, ExpressionType> symbolTable;
    private List<BaseCheck> checks;

    public Checker() {
        checks = new LinkedList<>();
        checks.add(new VariableReferenceCheck(this));
        checks.add(new DeclerationCheck(this));
        checks.add(new IfCheck(this));
        checks.add(new OperationCheck(this));
    }

    public void check(AST ast) {
        symbolTable = new SymbolTableImpl();

        checkNodes(ast.root);
    }

    private void checkNodes (ASTNode node) {
        for (ASTNode childNode:  node.getChildren() ) {
            constructsymboltable(childNode);

            checkNode(childNode);
            checkNodes(childNode);

            aftersymboltable(childNode);
        }
    }

    private void aftersymboltable(ASTNode childNode) {
        if(childNode instanceof Stylerule || childNode instanceof IfClause) {
            symbolTable.removeScope();
        }
    }
    // Heb net door mijn refactor de else clause scope regelen geborked.
    private void constructsymboltable(ASTNode childNode) {
        if(childNode instanceof Stylerule || childNode instanceof IfClause) {
            symbolTable.newScope();
        }
        if(childNode instanceof ElseClause) {
            symbolTable.removeScope();
            symbolTable.newScope();
        }
        if(childNode instanceof VariableAssignment) {
            VariableAssignment var = (VariableAssignment) childNode;
            symbolTable.assignSymbol(var.name.name, resolve_type_of_lit_op_varreference(var.expression));
        }
    }

    public ExpressionType resolve_type_of_lit_op_varreference(Expression ex) {
        // Literal direct
        if (ex instanceof Literal) {
            return ((Literal) ex).GetExpressionType();
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

    private void checkNode(ASTNode childNode) {
        //checkCH01CH06(childNode);
        //checkCH04(childNode); // Kan CH02 en CH04 samenvoegen.
        //checkCH02(childNode);
        //checkCH05(childNode);
        //checkCH03(childNode);
        //checkEigenCheck1(childNode);
        for (BaseCheck bc: checks) {
            bc.checkRule(childNode);
        }
    }
}
