package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;

public class VariableReferenceCheck extends BaseCheck{
    VariableReferenceCheck(Checker checker) {
        super(checker);
    }

    @Override
    public void checkRule(ASTNode node) {
        if (node instanceof VariableAssignment) {
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
    }

    private void checkCHO1internal(Expression expression) {
        if (expression instanceof VariableReference) {
            if(chr.resolve_type_of_lit_op_varreference(expression) == null)
                expression.setError("Variable has not been defined");
        }
    }
}
