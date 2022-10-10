package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Operation;

public class MultiplyOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Multiply";
    }

    @Override
    public int calculation(int val1, int val2) {
        return val1 * val2;
    }
}
