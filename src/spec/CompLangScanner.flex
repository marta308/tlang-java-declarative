package AST; // The generated parser will belong to package AST

import AST.CompLangParser.Terminals; // The terminals are implicitly defined in the parser

%%

// define the signature for the generated scanner
%public
%final
%class CompLangScanner 
%extends beaver.Scanner

// the interface between the scanner and the parser is the nextToken() method
%type beaver.Symbol 
%function nextToken 
%yylexthrow beaver.Scanner.Exception

// store line and column information in the tokens
%line
%column

// this code will be inlined in the body of the generated scanner class
%{
  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

WhiteSpace = [ ] | \t | \f | \n | \r | \r\n
Identifier = [:jletter:]([:jletterdigit:] | "." | "*")*

%%

// discard whitespace information
{WhiteSpace}  { }

// token definitions
"bind_copy"       { return sym(Terminals.BINDCOPY); }
"bind_exhaust"    { return sym(Terminals.BINDEXHAUST); }
{Identifier}  { return sym(Terminals.IDENTIFIER); }
";"           { return sym(Terminals.SEMI); }
<<EOF>>       { return sym(Terminals.EOF); }
