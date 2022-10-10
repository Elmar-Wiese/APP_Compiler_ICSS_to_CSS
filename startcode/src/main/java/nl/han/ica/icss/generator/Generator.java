package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;

import java.util.Iterator;

public class Generator {


	public static final String INDENTATION_SCOPE = "  ";

	public String generate(AST ast) {
		StringBuilder builder = new StringBuilder();

		walkthroughTreeStylesheet(builder, ast.root);

        return builder.toString();
	}

	private void walkthroughTreeStylesheet(StringBuilder sb, ASTNode node) {
		for (ASTNode childNode : node.getChildren()) {
			if (childNode instanceof Stylerule) {
				Selector sr = ((Stylerule) childNode).selectors.get(0);
				sb.append(sr.toString()).append(" {\n");

				walkthroughStylerule(sb, childNode);

				sb.append("}\n");
			}
		}
	}

	private void walkthroughStylerule(StringBuilder sb, ASTNode node) {
		for (ASTNode childNode : node.getChildren()) {
			if (childNode instanceof Declaration) {
				Declaration dec = (Declaration) childNode;
				sb.append(INDENTATION_SCOPE).append(dec.property.name).append(": ").append((dec.expression.toString())).append(";\n");
			}
		}
	}

}
