grammar Conf;

program
   : definition*
   ;

definition
   : (name '{' statement* '}')
   ;

statement
   :
   locationstmt
   | blockstmt+
   | paramstmt+
   | ifstmt+
   | returnstmt
   | expressionstmt
   | nullstmt
   ;

nullstmt
   : ';'
   ;

paramstmt
    : name STRING2 ';'
    ;

expressionstmt
   : rvalue ';'
   ;

locationstmt
    : 'location' STRING2 blockstmt
    ;

blockstmt
   : '{' statement* '}'
   ;

returnstmt
  : 'return' INT STRING2';'
  | 'return' INT name STRING2';'
  ;

ifstmt
   : 'if' '(' rvalue ')' statement ('else' statement)?
   ;


rvalue
   : expression
   | comparison
   | ternary
   | assignment
   ;

ternary
   : expression '?' rvalue ':' rvalue
   ;

comparison
   : expression binary rvalue
   ;

assignment
   : name assign rvalue
   ;

expression
   : ('(' rvalue ')')
   | name
   | STRING1
   | INT
   | FLOAT
   | (incdec name)
   | (name incdec)
   | (unary rvalue)
   | ('&' name)
   ;

assign
   : '=' binary?
   ;

incdec
   : '++'
   | '--'
   ;

unary
   : '-'
   | '!'
   ;

binary
   : '|'
   | '&'
   | '=='
   | '!='
   | '<'
   | '<='
   | '>'
   | '>='
   | '<<'
   | '>>'
   | '-'
   | '+'
   | '%'
   | '*'
   | '/'
   | '~'
   ;

lvalue
   : name
   | ('*' rvalue)
   | (rvalue '[' rvalue ']')
   ;

name
   : NAME
   ;


NAME
   : [a-zA-Z\\$] [a-zA-Z0-9_]*
   ;


INT
   : [0-9] +
   ;

FLOAT
   : [0-9] +'.'[0-9] +
   ;

STRING1
   : '"' ~ ["\r\n]* '"'
   ;
STRING2
   : '\'' ~ ['\r\n]* '\''
   ;

//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

BLOCKCOMMENT
   : '/*' .*? '*/' -> skip
   ;

LINE_COMMENT
    :   '#' ~[\r\n]* -> skip
    ;