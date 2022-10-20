package nl.han.ica.icss.checker;


import nl.han.ica.icss.ast.ASTNode;

public abstract class BaseCheck {
    protected Checker chr;

    BaseCheck(Checker checker) {
        this.chr = checker;
    }

    abstract public void checkRule(ASTNode node);
}
