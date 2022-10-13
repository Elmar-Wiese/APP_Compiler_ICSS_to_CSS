package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Operation;

public class ExponentiationOperation extends Operation {
    @Override
    public String getNodeLabel() {
        return "Exponent";
    }

    @Override
    public int calculation(int val1, int val2) {
        return (int) Math.pow(val1, val2);
    }
}
