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
stylesheet: stylerule* EOF;
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

stylerule: identity OPEN_BRACE declaration* CLOSE_BRACE; // This is probably greedy need to fix that someway. It's not greedy

// TODO
// Checken of LOWER_IDENT werkt voor alleen a of h1 enz.
identity: ID_IDENT | CLASS_IDENT | LOWER_IDENT; // Lower ident mag alleen bijv a of h1 zijn.

//LinkColor := #ff0000;
declare_variable: variable ASSIGNMENT_OPERATOR literal SEMICOLON;

variable: CAPITAL_IDENT;
// new Declaration("background-color")  .addChild(new ColorLiteral("#ffffff"))
// Declaration(String property)
// 	public PropertyName property;
//	public Expression expression;
declaration: propertyname COLON expression SEMICOLON;

// width of height
// Alleen de properties color, background-color, width en height zijn toegestaan.
propertyname: 'color' | 'background-color' | 'width' | 'height';

// Literal of Operation of  VariableReference
expression: literal | variable;

// BoolLiteral of ColorLiteral of PercentageLiteral of PixelLiteral of ScalarLiteral
boolliteral: TRUE | FALSE;
colorliteral: COLOR;
pixelliteral: PIXELSIZE;
percentageliteral: PERCENTAGE;
scalarliteral: SCALAR;
literal: boolliteral | colorliteral | pixelliteral | percentageliteral | scalarliteral;

