package nl.han.ica.datastructures;

import nl.han.ica.icss.ast.Expression;

import java.util.HashMap;

// Allebei de verschillende node versies laten overerven van een gezamelijke Node versie.
//Zo hoef je niet deze dupliciteit te hebben

//Waar ze gedefinieerd worden en in welke scope.
//Waar ze gebruikt worden.
public abstract class SymbolTableNode {

    IHANLinkedList<String> scope;

    public SymbolTableNode(IHANLinkedList<String> scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "SymbolTableNode{" +
                "scope=" + scope +
                '}';
    }
//    HashMap<String, SymbolTableNode> further;
//    Expression value;
//
//    SymbolTableNode() {
//        this.further = new HashMap<>();
//    }
//
//    SymbolTableNode(Expression value) {
//        this.value = value;
//    }
//
//
//    @Override
//    public String toString() {
//        if (further != null) {
//            return "SymbolTableNode{" +
//                    "further=" + further +
//                    '}';
//        }
//        return "SymbolTableNode{" +
//                "value=" + value +
//                '}';
//    }
}
