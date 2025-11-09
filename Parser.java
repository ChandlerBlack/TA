// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

public class Parser {

	private Scanner scanner;

	/**
	 * Checks if the current token matches the expected token type (s).
	 * If they match, the scanner advances to the next token; otherwise, a SyntaxException is thrown.
	 * @param s The expected token type (e.g., "id", "+", "(").
	 * @throws SyntaxException If the token does not match.
	 */	
	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	/**
	 * Returns the current token (the token the parser is currently looking at).
	 * * @return The current Token object.
	 * @throws SyntaxException If the scanner is in an invalid state.
	 */
	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	/**
	 * Returns the current character position in the source program.
	 * Used for detailed error reporting.
	 * @return The position index.
	 */
	private int pos() {
		return scanner.pos();
	}

	/**
	 * Parses a Mulop token ('*' or '/'), matching the 'mulop' grammar rule.
	 * @return A new NodeMulop if a mulop is found, otherwise null.
	 * @throws SyntaxException If an unexpected token is encountered.
	 */	
	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	/**
	 * Parses an Addop token ('+' or '-'), matching the 'addop' grammar rule.
	 * @return A new NodeAddop if an addop is found, otherwise null.
	 * @throws SyntaxException If an unexpected token is encountered.
	 */	
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	/**
	 * Parses a Factor, matching the 'fact' grammar rule: id, num, or (expr).
	 * This is where you will implement the unary minus logic.
	 * @return A NodeFact subclass (NodeFactId, NodeFactNum, NodeFactExpr).
	 * @throws SyntaxException If the factor is not valid.
	 */	
	private NodeFact parseFact() throws SyntaxException {
		// handling unary minus
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactUnaryMinus(fact);
		}

		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	/**
	 * Parses a Term, matching the 'term' grammar rule: fact mulop term | fact.
	 * It builds the term structure using right recursion.
	 * @return A NodeTerm object.
	 * @throws SyntaxException If a syntax error is found in the term structure.
	 */	
	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	/**
	 * Parses an Expression, matching the 'expr' grammar rule: term addop expr | term.
	 * It builds the expression structure using right recursion.
	 * @return A NodeExpr object.
	 * @throws SyntaxException If a syntax error is found in the expression structure.
	 */	
	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	/**
	 * Parses an Assignment statement, matching the 'assn' grammar rule: id = expr.
	 * @return A NodeAssn object.
	 * @throws SyntaxException If the assignment structure is invalid.
	 */	
	private NodeAssn parseAssn() throws SyntaxException {
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}

	/**
	 * Parses a Statement, matching the 'stmt' grammar rule: assn ';'.
	 * @return A NodeStmt object.
	 * @throws SyntaxException If the statement structure is invalid.
	 */	
	private NodeStmt parseStmt() throws SyntaxException {
		NodeAssn assn = parseAssn();
		match(";");
		NodeStmt stmt = new NodeStmt(assn);
		return stmt;
	}

	/**
	 * The entry point for the parser. It takes the entire program string, 
	 * initializes the scanner, and attempts to parse a complete program (stmt).
	 * @param program The source program string to parse.
	 * @return The root Node of the constructed Abstract Syntax Tree (AST).
	 * @throws SyntaxException If a syntax error is found.
	 */	
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		NodeStmt stmt = parseStmt();
		match("EOF");
		return stmt;
	}

}
