package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.IfClause;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;

public class IfCheck extends BaseCheck{
    IfCheck(Checker checker) {
        super(checker);
    }

    @Override
    public void checkRule(ASTNode node) {
        if(!(node instanceof IfClause))
            return;

        IfClause ifnode = (IfClause) node;
//        boolean correct = false;
//        if (ifnode.conditionalExpression instanceof BoolLiteral) {
//            correct = true;
//        }
//        if(ifnode.conditionalExpression instanceof VariableReference) {
//            //if ( symbolTable.getValue(((VariableReference) ifnode.conditionalExpression).name ) instanceof BoolLiteral) {
//            if ( symbolTable.getValue(((VariableReference) ifnode.conditionalExpression).name ) == ExpressionType.BOOL ) {
//                correct = true;
//            }
//        }

        if(chr.resolve_type_of_lit_op_varreference(ifnode.getConditionalExpression()) != ExpressionType.BOOL) {
            ifnode.setError("If statement condition is not boolean");
        }
    }
}
