package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.BooleanExpression;
import nl.han.ica.icss.ast.booloperations.AndOperation;
import nl.han.ica.icss.ast.booloperations.LessThanOperation;
import nl.han.ica.icss.ast.booloperations.NotOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

public class BooleanExpressionChecker extends BaseCheck{
    BooleanExpressionChecker(Checker checker) {
        super(checker);
    }

    @Override
    public void checkRule(ASTNode node) {
        if(!(node instanceof BooleanExpression))
            return;

        if (node instanceof NotOperation) {
            NotOperation no = (NotOperation) node;
            if (chr.resolve_type_of_lit_op_varreference(no.value) != ExpressionType.BOOL)
                no.setError("Only boolean values can be used with Not Operator");
        }
        else if (node instanceof AndOperation) {
            AndOperation ao = (AndOperation) node;
            if (chr.resolve_type_of_lit_op_varreference(ao.lhs) != ExpressionType.BOOL ||
                chr.resolve_type_of_lit_op_varreference(ao.rhs) != ExpressionType.BOOL) {
                node.setError("The values of a boolean comparison must be boolean.");
            }
        } else if (node instanceof LessThanOperation) {
            ExpressionType left = chr.resolve_type_of_lit_op_varreference(((LessThanOperation)node).lhs);
            ExpressionType right = chr.resolve_type_of_lit_op_varreference(((LessThanOperation)node).rhs);
            if (left != right) {
                node.setError("The values of the comparison operation must be of the same type");
            } else if ( left != ExpressionType.PERCENTAGE && right != ExpressionType.PERCENTAGE &&
                        left != ExpressionType.PIXEL && right != ExpressionType.PIXEL &&
                        left != ExpressionType.SCALAR && right != ExpressionType.SCALAR ){
                node.setError("The values of the comparison operation must be a Number");
            }
        }
    }

}
