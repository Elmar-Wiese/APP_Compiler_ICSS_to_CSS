grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;


OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
EXPONENT: '^';
ASSIGNMENT_OPERATOR: ':=';
PARENTHESES_OPEN: '(';
PARENTHESES_CLOSE: ')';

LINE_COMMENT: '//' .*? ('\n' | EOF) -> skip;
BlOCK_COMMENT: '/*' .*? '*/' -> skip;
// Meerdere block comments in elkaar kan niet in dit g4 file dus ik doe dat ook niet.
// /*  /* hoi */ */

// Boolean operators
AND: '&&';
NOT: '!';
LESS_THAN: '<';
EQUALS: '==';

//--- PARSER: ---

stylesheet: node* EOF;

node: stylerule | declare_variable;

stylerule: identity OPEN_BRACE body CLOSE_BRACE;

identity: (ID_IDENT | COLOR) | CLASS_IDENT | LOWER_IDENT;

body: (declare_variable | declaration | if_statement)+;

declaration: propertyname COLON expression SEMICOLON;

declare_variable: variable ASSIGNMENT_OPERATOR expression SEMICOLON;

variable: CAPITAL_IDENT;

propertyname: 'color' | 'background-color' | 'width' | 'height';

expression: expression_non_recur | operation | boolean_expression;
expression_non_recur: literal | variable;

boolliteral: TRUE | FALSE;
colorliteral: COLOR;
pixelliteral: PIXELSIZE;
percentageliteral: PERCENTAGE;
scalarliteral: SCALAR;
literal: boolliteral | colorliteral | pixelliteral | percentageliteral | scalarliteral;

operation:
        | PARENTHESES_OPEN operation PARENTHESES_CLOSE
        | operation EXPONENT operation
        | operation MUL operation
        | operation (PLUS|MIN) operation
        | expression_non_recur;

 boolean_expression: expression_non_recur
                    | PARENTHESES_OPEN boolean_expression PARENTHESES_CLOSE
                    | NOT boolean_expression
                    | boolean_expression (EQUALS | LESS_THAN) boolean_expression
                    | boolean_expression AND boolean_expression;

if_statement:   IF BOX_BRACKET_OPEN boolean_expression BOX_BRACKET_CLOSE
                OPEN_BRACE body CLOSE_BRACE
                else_statement ?;

else_statement: ELSE OPEN_BRACE body CLOSE_BRACE;



