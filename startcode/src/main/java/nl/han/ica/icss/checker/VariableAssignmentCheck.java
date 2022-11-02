package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.VariableAssignment;

public class VariableAssignmentCheck extends BaseCheck{
    VariableAssignmentCheck(Checker checker) {
        super(checker);
    }

    @Override
    public void checkRule(ASTNode node) {
        if (!(node instanceof VariableAssignment))
            return;

        VariableAssignment va = (VariableAssignment) node;
        if (    chr.symbolTable.getValue(va.name.name) != null &&
                chr.resolve_type_of_lit_op_varreference(va.expression) != chr.symbolTable.getValue(va.name.name))
            va.setError("Variables must be of one type.");
    }
}
