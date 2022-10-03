// Generated from C:/SchoolJaar3/APP/Compiler_Opdracht/APP_Compiler_ICSS_to_CSS/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#identity}.
	 * @param ctx the parse tree
	 */
	void enterIdentity(ICSSParser.IdentityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#identity}.
	 * @param ctx the parse tree
	 */
	void exitIdentity(ICSSParser.IdentityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propertyname}.
	 * @param ctx the parse tree
	 */
	void enterPropertyname(ICSSParser.PropertynameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propertyname}.
	 * @param ctx the parse tree
	 */
	void exitPropertyname(ICSSParser.PropertynameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolliteral}.
	 * @param ctx the parse tree
	 */
	void enterBoolliteral(ICSSParser.BoolliteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolliteral}.
	 * @param ctx the parse tree
	 */
	void exitBoolliteral(ICSSParser.BoolliteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ICSSParser.LiteralContext ctx);
}