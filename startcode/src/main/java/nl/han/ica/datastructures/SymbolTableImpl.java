package nl.han.ica.datastructures;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Literal;

import java.util.HashMap;
import java.util.List;

public class SymbolTableImpl<K, V> implements ISymbolTable<K, V>{
    IHANLinkedList<HashMap<K, V>> symbolsScope;

    public SymbolTableImpl() {
        clear();
    }

    public void clear() {
        HashMap globalscope = new HashMap();
        symbolsScope = new LinkedListImpl<>();
        symbolsScope.addFirst(globalscope);
    }

    @Override
    public void newScope() {
        symbolsScope.insert(symbolsScope.getSize() , new HashMap<>());
    }

    @Override
    public void removeScope() {
        symbolsScope.delete(symbolsScope.getSize() - 1);
    }

    @Override
    public void assignSymbol(K name, V value) {
        HashMap<K, V> currentScope = symbolsScope.get(symbolsScope.getSize() - 1);
        currentScope.put(name, value);
    }

    @Override
    public V getValue(K name) {
        for (int i = symbolsScope.getSize() - 1; i >= 0; i--) {
            HashMap<K, V> scope = symbolsScope.get(i);
            if(getMapValue(scope, name) != null) {
                return getMapValue(scope, name);
            }
        }
        return null;
    }

    private V getMapValue(HashMap<K, V> scope ,K name) {
        return scope.get(name);
    }

//    public Expression getSymbol(String name, int scope) {
//
//    }

//    public void assignSymbol(String name, Expression value, int scope) {
//        if (symbolTable.getSize() < scope) {
//            HashMap<String, Expression> newScope = new HashMap<>();
//            newScope.put(name, value);
//            symbolTable.insert(scope, newScope);
//            //symbolTable.put(name, new SymbolTableNode(value, symbolTable.get(name).scope));
//        } else {
//            //symbolTable.put(name, new SymbolTableNode(value, scope));
//            HashMap<String, Expression> symbolScope = symbolTable.get(scope);
//            symbolScope.put()
//        }
//    }

//    @Override
//    public String toString() {
//        return "SymbolTableImpl{" +
//                "symbolTable=" + symbolTable +
//                '}';
//    }


//    HashMap<String, SymbolTableNode> symbolTable;
//
//    public void add(String name, Expression value) {
//        symbolTable.put(name, new SymbolTableNode(value));
//    }
//
//    public void newScope(String scopename) {
//        symbolTable.put(scopename, new SymbolTableNode());
//    }
//
//    public Expression get(IHANQueue allowedScopes, String name) {
//        //Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
//        //while (it.hasNext()) {
//        //    Map.Entry<Integer, Integer> pair = it.next();
//        //    i += pair.getKey() + pair.getValue();
//        //}
//
//    }
//
//    private Expression iterateNode(SymbolTableNode node,List<String> allowedScopes, String name) {
//
//    }
//    IHANLinkedList<HashMap<String, Object>> variables; // list i 0 is global
//    //HashMap<String, Object> global;
//    int scope = 0;
//
//    public SymbolTableImpl() {
//        variables = new LinkedListImpl<>();
//        variables.addFirst(new HashMap<>()); // global
//    }
//
//    public void addGlobal(String nameVar, Object value) { // Think this one is extra and should be removed
//        variables.getFirst().put(nameVar, value);
//    }
//
//    public void addVariable(int scopelvl, String nameVar, Object value) {
//
//    }
//
//    public Object getValue(String nameVar) {
//        for (int i = scope; i > 0; i--) {
//            HashMap<String, Object> currentScopeVars = variables.get(scope);
//            if(currentScopeVars.get(nameVar) != null) {
//                // Ik ga niet rekening houden met dat value van variabelen null kan zijn en dat moet worden gecheckt met containskey
//                return currentScopeVars.get(nameVar);
//            }
//        }
//        return null;
////        int i = scope;
////        while(i > 0 | )
//    }
//
//    private void lowerScope(int newScope) {
//        for (int i = scope; i > newScope ; i++) {
//            variables.delete(i);
//        }
//    }
//
//    private void increaseScope() {
//        variables.insert(variables.getSize(), new HashMap<>());
//    }
}
