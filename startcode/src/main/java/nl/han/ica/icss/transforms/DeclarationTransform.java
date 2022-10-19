package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.booloperations.EqualsOperation;

public class DeclarationTransform extends BaseTransform{
    public DeclarationTransform(Evaluator ev) {
        super(ev);
    }

    @Override
    public void transform(ASTNode childNode, ASTNode parent) {
//        if (childNode instanceof VariableReference && parent instanceof Declaration) {
//            VariableReference var = (VariableReference) childNode;
//            replaceNode(parent, childNode, ev.expressionToLiteral(var));
//        }
        if (parent instanceof Declaration && childNode instanceof Expression) {
            replaceNode(parent, childNode, ev.expressionToLiteral((Expression) childNode));
        }
//        if (!(childNode instanceof Literal)) {
//            if (parent instanceof Declaration) {
//                Expression ex = ((Declaration) parent).expression;
//                replaceNode(parent, childNode, ev.expressionToLiteral(ex));
//            }
//        }
    }
}
