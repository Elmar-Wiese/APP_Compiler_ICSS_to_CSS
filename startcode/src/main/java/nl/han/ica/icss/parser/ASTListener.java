package nl.han.ica.icss.parser;


import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.datastructures.StackImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.booloperations.AndOperation;
import nl.han.ica.icss.ast.booloperations.EqualsOperation;
import nl.han.ica.icss.ast.booloperations.LessThanOperation;
import nl.han.ica.icss.ast.booloperations.NotOperation;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.ExponentiationOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener{
	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new StackImpl<>();
		currentContainer.push(ast.root);
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule newRule = new Stylerule();
		currentContainer.peek().addChild(newRule);
		currentContainer.push(newRule);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterIdentity(ICSSParser.IdentityContext ctx) {
		Selector newNode = null;
		if (ctx.CLASS_IDENT() != null) {
			newNode = new ClassSelector(ctx.CLASS_IDENT().getText());
		} else
		if (ctx.ID_IDENT() != null) {
			newNode = new IdSelector(ctx.ID_IDENT().getText());
		} else
		if (ctx.LOWER_IDENT() != null) {
			newNode = new TagSelector(ctx.LOWER_IDENT().getText());
		} else {
			newNode = new IdSelector(ctx.COLOR().getText());
		}
		currentContainer.peek().addChild(newNode);
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration newDec = new Declaration(ctx.propertyname().getText());
		currentContainer.peek().addChild(newDec);
		currentContainer.push(newDec);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterDeclare_variable(ICSSParser.Declare_variableContext ctx) {
		VariableAssignment newVariable = new VariableAssignment();
		currentContainer.peek().addChild(newVariable);
		currentContainer.push(newVariable);
	}

	@Override
	public void exitDeclare_variable(ICSSParser.Declare_variableContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterVariable(ICSSParser.VariableContext ctx) {
		currentContainer.peek().addChild(new VariableReference(ctx.getText()));
	}


//	private <T> T ctxNullCheckToWhatever (List<Map<Predicate<? super T>, T>> predicates) {
//
//		List<Integer> list = new LinkedList<>();
//		list.stream().
//	}

	@Override
	public void enterBoolliteral(ICSSParser.BoolliteralContext ctx) {
		currentContainer.peek().addChild(new BoolLiteral(ctx.getText()));
	}

	@Override
	public void enterColorliteral(ICSSParser.ColorliteralContext ctx) {
		currentContainer.peek().addChild(new ColorLiteral(ctx.getText()));
	}

	@Override
	public void enterPixelliteral(ICSSParser.PixelliteralContext ctx) {
		currentContainer.peek().addChild(new PixelLiteral(ctx.getText()));
	}

	@Override
	public void enterPercentageliteral(ICSSParser.PercentageliteralContext ctx) {
		currentContainer.peek().addChild(new PercentageLiteral(ctx.getText()));
	}

	@Override
	public void enterScalarliteral(ICSSParser.ScalarliteralContext ctx) {
		currentContainer.peek().addChild(new ScalarLiteral(ctx.getText()));
	}

	@Override
	public void enterOperation(ICSSParser.OperationContext ctx) {
		if(ctx.expression_non_recur() != null || isNotStackedOperation(ctx.getChild(1).getText())) {
			return;
		}
		Operation operation = null;
		switch (ctx.getChild(1).getText()) {
			case "*":
				operation = new MultiplyOperation();
				break;
			case "+":
				operation = new AddOperation();
				break;
			case "-":
				operation = new SubtractOperation();
				break;
			case "^":
				operation = new ExponentiationOperation();
				break;
		}
		currentContainer.peek().addChild(operation);
		currentContainer.push(operation);
	}

	@Override
	public void exitOperation(ICSSParser.OperationContext ctx) {
		if(ctx.expression_non_recur() != null || isNotStackedOperation(ctx.getChild(1).getText())) {
			return;
		}
		currentContainer.pop();
	}

	private boolean isNotStackedOperation(String child1) {
		switch (child1) {
			case "*":
				return false;
			case "+":
				return false;
			case "-":
				return false;
			case "^":
				return false;
		}
		return true;
	}

	@Override
	public void enterIf_statement(ICSSParser.If_statementContext ctx) {
		IfClause newIf = new IfClause();
		currentContainer.peek().addChild(newIf);
		currentContainer.push(newIf);
	}

	@Override
	public void exitIf_statement(ICSSParser.If_statementContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterElse_statement(ICSSParser.Else_statementContext ctx) {
		push(new ElseClause());
	}

	@Override
	public void exitElse_statement(ICSSParser.Else_statementContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterBoolean_expression(ICSSParser.Boolean_expressionContext ctx) {
		if (ctx.expression_non_recur() != null || !(isStackedBooleanExpression(ctx.getChild(1).getText()) || ctx.getChild(0).getText().equals("!")))
			return;

		BooleanExpression operation = null;
		switch (ctx.getChild(1).getText()) {
			case "&&":
				operation = new AndOperation();
				break;
			case "==":
				operation = new EqualsOperation();
				break;
			case "<":
				operation = new LessThanOperation();
				break;
			default:
				//if (ctx.getChild(0).getText().equals("!")) {
					operation = new NotOperation();
				//}
		}

		push(operation);
	}

	private boolean isStackedBooleanExpression(String text) {
		switch (text) {
			case "==":
				return true;
			case "<":
				return true;
			case "&&":
				return true;
		}
		return false;
	}

	@Override
	public void exitBoolean_expression(ICSSParser.Boolean_expressionContext ctx) {
		if (ctx.expression_non_recur() != null || !(isStackedBooleanExpression(ctx.getChild(1).getText()) || ctx.getChild(0).getText().equals("!")))
			return;
		currentContainer.pop();
	}

	private void push(ASTNode node) {
		currentContainer.peek().addChild(node);
		currentContainer.push(node);
	}

	private void add(ASTNode node) {
		currentContainer.peek().addChild(node);
	}

}