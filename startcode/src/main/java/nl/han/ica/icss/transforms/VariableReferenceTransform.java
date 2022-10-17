package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.VariableReference;

public class VariableReferenceTransform extends BaseTransform{
    public VariableReferenceTransform(Evaluator ev) {
        super(ev);
    }

    @Override
    public void transform(ASTNode childNode, ASTNode parent) {
        if (childNode instanceof VariableReference && parent instanceof Declaration) {
            VariableReference var = (VariableReference) childNode;
            replaceNode(parent, childNode, ev.getSymbolTable().getValue(var.name));
        }
    }
}
