package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Operation;

public class AddOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Add";
    }

    @Override
    public int calculation(int val1, int val2) {
        return val1 + val2;
    }
}
