package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;

import java.util.Optional;

public class IfElseEvaluator extends BaseTransform{
    public IfElseEvaluator(Evaluator ev) {
        super(ev);
    }

    @Override
    public void transform(ASTNode childNode, ASTNode parent) {
        if(!(childNode instanceof IfClause))
            return;

        IfClause ifClause = (IfClause) childNode;
        BoolLiteral condition = (BoolLiteral) ev.expressionToLiteral(ifClause.conditionalExpression);
        if (condition.value) {
            ev.evaluateNodes(childNode);
            replaceNodeWithNodes(parent, childNode, ifClause.body);
        } else {
            Optional<ElseClause> optionalElseClause = Optional.ofNullable(ifClause.elseClause);
            if(optionalElseClause.isPresent()) {
                ev.evaluateNodes(childNode);
                replaceNodeWithNodes(parent, childNode, optionalElseClause.get().body);
            } else {
                replaceNodeWithNodes(parent, childNode, null);
            }
        }

    }


}
