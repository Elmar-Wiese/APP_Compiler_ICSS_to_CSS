package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.ExponentiationOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

public class OperationCheck extends BaseCheck {
    OperationCheck(Checker checker) {
        super(checker);
    }

    @Override
    public void checkRule(ASTNode node) {
        if (!(node instanceof Operation))
            return;

        Operation op = (Operation) node;
        ExpressionType lhs = chr.resolve_type_of_lit_op_varreference(op.lhs);
        ExpressionType rhs = chr.resolve_type_of_lit_op_varreference(op.rhs);

        // plus en min operanden gelijk
        if (op instanceof AddOperation || op instanceof SubtractOperation) { // K
            if (!(lhs == rhs))
                op.setError("Add operation values must be off same type");
        } else if (op instanceof MultiplyOperation) { // 1 scalaire waarde
            if (!(lhs == ExpressionType.SCALAR || rhs == ExpressionType.SCALAR))
                op.setError("One of the values of a multiply operation must be scalar");
        } else if (lhs != ExpressionType.SCALAR || rhs != ExpressionType.SCALAR)
            node.setError("Exponentiation operations can only be done on scalar values and/or use scalar values");
        if (lhs == ExpressionType.COLOR || rhs == ExpressionType.COLOR) {
            node.setError("Don't use colours in operations");
        } else if (lhs == ExpressionType.BOOL || rhs == ExpressionType.BOOL) {
            node.setError("Don't use booleans in operations");
        }
//        for (ASTNode ex : op.getChildren()) {
//            if (!(ex instanceof Operation)) {
//                ExpressionType type = resolve_type_of_lit_op_varreference((Expression) ex);
//                if (type == ExpressionType.COLOR)
//                    node.setError("Don't use colours in operations");
//                if (type == ExpressionType.BOOL)
//                    node.setError("Don't use booleans in operations");
//            }
//        }
    }
}
