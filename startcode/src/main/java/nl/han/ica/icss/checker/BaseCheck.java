package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.SymbolTableImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

public abstract class BaseCheck {
    protected Checker chr;

    BaseCheck(Checker checker) {
        this.chr = checker;
    }

    abstract public void checkRule(ASTNode node);
}
