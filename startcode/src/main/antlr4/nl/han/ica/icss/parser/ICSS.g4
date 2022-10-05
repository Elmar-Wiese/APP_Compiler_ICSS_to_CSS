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

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';




//--- PARSER: ---
//stylesheet.addChild((new Stylerule())
stylesheet: node* EOF;
//.addChild(new TagSelector("p"))
//				.addChild((new Declaration("background-color"))
//                        .addChild(new ColorLiteral("#ffffff")))
//				.addChild((new Declaration("width"))
//						.addChild(new PixelLiteral("500px")))

//		stylesheet.addChild((new VariableAssignment())
//                .addChild(new VariableReference("LinkColor"))
//                .addChild(new ColorLiteral("#ff0000"))
//        );
node: stylerule | declare_variable;

stylerule: identity OPEN_BRACE body CLOSE_BRACE; // This is probably greedy need to fix that someway. It's kinda greedy.
// it finds the first close brace after the open brace.
//  a {
//  	color: LinkColor;
//	    if[UseLinkColor]{ error
//	        width: ParWidth;
//	    } // CLOSE_BRACE it chooses
//  }

//OPEN_BRACE (declaration | if_statement)* CLOSE_BRACE
body: (declare_variable | declaration | if_statement)+; // VARIABLE DECLAREN of DECLERATIONS of IF STATEMENT ZIJN
// new Declaration("background-color")  .addChild(new ColorLiteral("#ffffff"))
// Declaration(String property)
// 	public PropertyName property;
//	public Expression expression;
declaration: propertyname COLON expression SEMICOLON;
// TODO
// Checken of LOWER_IDENT werkt voor alleen a of h1 enz.
identity: ID_IDENT | CLASS_IDENT | LOWER_IDENT; // Lower ident mag alleen bijv a of h1 zijn.

//LinkColor := #ff0000;
//Textcolor := Bgcolor;
declare_variable: variable ASSIGNMENT_OPERATOR expression SEMICOLON;

variable: CAPITAL_IDENT;


// width of height
// Alleen de properties color, background-color, width en height zijn toegestaan.
propertyname: 'color' | 'background-color' | 'width' | 'height';

// Literal of Operation of  VariableReference
expression: expression_non_recur | operation;
expression_non_recur: literal | variable;

// BoolLiteral of ColorLiteral of PercentageLiteral of PixelLiteral of ScalarLiteral
boolliteral: TRUE | FALSE;
colorliteral: COLOR;
pixelliteral: PIXELSIZE;
percentageliteral: PERCENTAGE;
scalarliteral: SCALAR;
literal: boolliteral | colorliteral | pixelliteral | percentageliteral | scalarliteral;


// Operation
//operation: add_operation | multiply_operation | substract_operation;
//add_operation: operation PLUS operation;
//multiply_operation: operation MUL operation;
//substract_operation: operation MIN operation;
operation:
//      | operation DIVIDE operation
        | operation MUL operation
        | operation PLUS operation
        | operation MIN operation
        | expression_non_recur;

if_statement:   IF BOX_BRACKET_OPEN boolean_expression BOX_BRACKET_CLOSE
                OPEN_BRACE body CLOSE_BRACE
                (ELSE OPEN_BRACE body CLOSE_BRACE) ?;

//3<5, Value==5, !AdjustWidth
 boolean_expression: variable | boolliteral;