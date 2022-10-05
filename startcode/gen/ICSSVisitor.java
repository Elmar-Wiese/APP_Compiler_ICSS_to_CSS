// Generated from C:/SchoolJaar3/APP/Compiler_Opdracht/APP_Compiler_ICSS_to_CSS/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(ICSSParser.NodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#identity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentity(ICSSParser.IdentityContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declare_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare_variable(ICSSParser.Declare_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(ICSSParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propertyname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyname(ICSSParser.PropertynameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression_non_recur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_non_recur(ICSSParser.Expression_non_recurContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolliteral(ICSSParser.BoolliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorliteral(ICSSParser.ColorliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#pixelliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixelliteral(ICSSParser.PixelliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#percentageliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentageliteral(ICSSParser.PercentageliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#scalarliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarliteral(ICSSParser.ScalarliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(ICSSParser.OperationContext ctx);
}