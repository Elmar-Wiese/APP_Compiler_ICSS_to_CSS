package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.types.ExpressionType;
// 'color' | 'background-color'
// ColorLiteral
// | 'width' | 'height'
// PercantageLiteral, PixelLiteral
public class DeclerationCheck extends BaseCheck{

    DeclerationCheck(Checker checker) {
        super(checker);
    }

    @Override
    public void checkRule(ASTNode node) {
        if (!(node instanceof Declaration)) {
            return;
        }
        Declaration declaration = ((Declaration) node);

        ExpressionType expressionType = chr.resolve_type_of_lit_op_varreference(declaration.expression);
        if (declaration.property.name.equals("color")  || declaration.property.name.equals("background-color")) {
            if (expressionType != ExpressionType.COLOR)
                declaration.setError("color declarations must be assigned hexadecimal values");
        }
        if (declaration.property.name.equals("width") || declaration.property.name.equals("height")) {
            if (!(expressionType == ExpressionType.PERCENTAGE || expressionType == ExpressionType.PIXEL))
                declaration.setError("size declarations must be assigned percentage or pixel values");

        }
    }
}
