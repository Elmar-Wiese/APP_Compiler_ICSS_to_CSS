package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.ASTNode;

import java.util.List;

abstract public class BaseTransform {
    protected Evaluator ev;

    public BaseTransform(Evaluator ev) {
        this.ev = ev;
    }

    // Single child
    protected void replaceNode(ASTNode parent, ASTNode oldNode ,ASTNode newNode) {
        parent.removeChild(oldNode);
        parent.addChild(newNode);
    }

    // Like for bodies of stylerules and if statements.
    protected void replaceNodeWithNodes(ASTNode parent, ASTNode oldNode, List<ASTNode> replacement) {
        parent.removeChild(oldNode);
        if (replacement != null) {
            for (ASTNode replace : replacement) {
                parent.addChild(replace);
            }
        }
    }

    abstract public void transform(ASTNode childNode, ASTNode parent);
}
