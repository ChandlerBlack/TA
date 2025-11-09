// Author - https://github.com/JimBuffenbarger/dws
// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

	private String token;
	private String lexeme;

	public Token(String token, String lexeme) {
		this.token=token;
		this.lexeme=lexeme;
	}

	/**
	 * Constructs a Token where the lexeme is the same as the token type 
	 * (used for operators and reserved keywords like '+' or '(').
	 * * @param token The token type (e.g., "+", "id").
	 */	
	public Token(String token) {
		this(token,token);
	}

	/**
	 * Returns the token type (e.g., "id", "num", "+").
	 * * @return The token type string.
	 */	
	public String tok() { return token; }

	/**
	 * Returns the token's lexeme (the actual characters, e.g., "x", "123").
	 * * @return The token's lexeme string.
	 */	
	public String lex() { return lexeme; }

	/**
	 * Checks if this token's type is equal to another token's type.
	 * Note: This only compares token types, not lexemes.
	 * * @param t The other Token to compare against.
	 * @return True if the token types are equal, false otherwise.
	 */	
	public boolean equals(Token t) {
		return token.equals(t.token);
	}

	public String toString() {
		return "<"+tok()+","+lex()+">";
	}

}
