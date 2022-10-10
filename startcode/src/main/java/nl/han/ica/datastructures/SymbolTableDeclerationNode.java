package nl.han.ica.datastructures;

import nl.han.ica.icss.ast.Expression;

public class SymbolTableDeclerationNode extends SymbolTableNode{
    Expression value;

    public SymbolTableDeclerationNode(Expression value, IHANLinkedList<String> scope) {
        super(scope);
        this.value = value;
    }
}
