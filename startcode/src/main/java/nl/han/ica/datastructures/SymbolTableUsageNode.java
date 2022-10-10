package nl.han.ica.datastructures;

import nl.han.ica.icss.ast.Expression;

public class SymbolTableUsageNode extends SymbolTableNode{
    public SymbolTableUsageNode(Expression value, IHANLinkedList<String> scope) {
        super(scope);
    }
}
