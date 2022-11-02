package nl.han.ica.datastructures;

import java.util.HashMap;

public interface ISymbolTable<K, V> {
    //I Honestly don't care

    void newScope();

    void removeScope();

    public void assignSymbol(K name, V value);

    V getValue(K name);
}
