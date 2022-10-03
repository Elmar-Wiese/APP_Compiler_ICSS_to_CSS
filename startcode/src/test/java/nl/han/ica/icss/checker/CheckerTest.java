package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
    AST ast;
    Stylesheet stylesheet;
    Checker checker;

    List<String> errors;

    @BeforeEach
    void SetUp() {
        ast = new AST();
        stylesheet = ast.root;
        checker = new Checker();
        errors = new LinkedList<>();
    }

    @Test
    void testCH04Valid() {
        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("500px")))
        );

        checker.check(ast);

        assertTrue(checkForErrors());
    }

    @Test
    void testCH04NonColourLiteralAtColourDecleration() {
        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new PixelLiteral("500px")))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("500px")))
        );

        checker.check(ast);

        assertFalse(checkForErrors());
    }

    @Test
    void testCH04NonPixelorPercantageLiteralAtSizeDecleration() {
        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("height"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("width"))
                        .addChild(new ScalarLiteral("22")))
        );

        checker.check(ast);

        assertFalse(checkForErrors());
    }

    private boolean checkForErrors() {

        ArrayList<SemanticError> errors = this.ast.getErrors();
        if (!errors.isEmpty()) {
            for (SemanticError e : errors) {
                this.errors.add(e.toString());
            }
        }

        return errors.isEmpty();
    }
}