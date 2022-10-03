package nl.han.ica.icss.parser;


import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.datastructures.StackImpl;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
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
		}
		if (ctx.ID_IDENT() != null) {
			newNode = new IdSelector(ctx.ID_IDENT().getText());
		}
		if (ctx.LOWER_IDENT() != null) {
			newNode = new TagSelector(ctx.LOWER_IDENT().getText());
		}
		currentContainer.peek().addChild(newNode);
	}

	@Override
	public void exitIdentity(ICSSParser.IdentityContext ctx) {
		// nothing was pushed
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration newDec = new Declaration(ctx.propertyname().getText());
		//System.out.println(ctx.getText());
		currentContainer.peek().addChild(newDec);
		currentContainer.push(newDec);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.pop();
	}

	// propertyname: 'color' | 'background-color' | 'width' | 'height';
//	@Override
//	public void enterPropertyname(ICSSParser.PropertynameContext ctx) {
//		super.enterPropertyname(ctx);
//	}
//
//	@Override
//	public void exitPropertyname(ICSSParser.PropertynameContext ctx) {
//		super.exitPropertyname(ctx);
//	}


//	@Override
//	public void enterExpression(ICSSParser.ExpressionContext ctx) {
//		super.enterExpression(ctx);
//		Expression newEx = null;
//		if(ctx.literal() != null) {
//			if(ctx.literal().TRUE() != null) {
//
//			}
//		}
//	}

//	@Override
//	public void exitExpression(ICSSParser.ExpressionContext ctx) {
//		super.exitExpression(ctx);
//	}

	// TODO I have added seperate boolliteral. Move the literal out of this class.
	@Override
	public void enterLiteral(ICSSParser.LiteralContext ctx) {
		Literal newLit = null;
		if(ctx.TRUE() != null || ctx.FALSE() != null) {
			newLit = new BoolLiteral(ctx.getText());
		}
		if(ctx.COLOR() != null) {
			newLit = new ColorLiteral(ctx.getText());
		}
		if(ctx.PERCENTAGE() != null) {
			newLit = new PercentageLiteral(ctx.getText());
		}
		if(ctx.PIXELSIZE() != null) {
			newLit = new PixelLiteral(ctx.getText());
		}
		if(ctx.SCALAR() != null) {
			newLit = new ScalarLiteral(ctx.getText());
		}
		currentContainer.peek().addChild(newLit);
	}

//	private <T> T ctxNullCheckToWhatever (List<Map<Predicate<? super T>, T>> predicates) {
//
//		List<Integer> list = new LinkedList<>();
//		list.stream().
//	}

	@Override
	public void exitLiteral(ICSSParser.LiteralContext ctx) {
		super.exitLiteral(ctx);
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		super.exitEveryRule(ctx);
	}


}