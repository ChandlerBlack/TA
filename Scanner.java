// Author - https://github.com/JimBuffenbarger/dws
// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

public class Scanner {

	private String program;		// source program being interpreted
	private int pos;			// index of next char in program
	private Token token;		// last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();

	// initializers for previous sets

	/**
	 * Helper method to populate a Set with a range of characters.
	 * @param s The Set to be populated.
	 * @param lo The starting character (inclusive).
	 * @param hi The ending character (inclusive).
	 */
	private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}

	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	private void initDigits(Set<String> s) {
		fill(s,'0','9');
	}

	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}

	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
	}

	private void initKeywords(Set<String> s) {
	}

	// constructor:
	//     - squirrel-away source program ----- as opposed to mouse away
	//     - initialize sets

	/**
	 * Constructs the Scanner by initializing character sets (whitespace, digits, etc.) 
	 * and preparing to scan a new program.
	 * * @param program The source program string to be scanned.
	 */	
	public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
	}

	// handy string-processing methods

	/**
	 * Checks if the scanner has reached the end of the program string.
	 * * @return True if pos is past the end of the program, false otherwise.
	 */	
	public boolean done() {
		return pos>=program.length();
	}

	/**
	 * Advances the position (pos) past a sequence of characters contained in the given set.
	 * Used primarily for skipping whitespace.
	 * * @param s The Set of characters to skip.
	 */	
	private void many(Set<String> s) {
		while (!done()&&s.contains(program.charAt(pos)+""))
			pos++;
	}

	// This method advances the scanner,
	// until the current input character
	// is just after a sequence of one or more
	// of a particular character.
	// Arguments:
	//     c = the character to search for
	// Members:
	//     program = the scanner's input
	//     pos = index of current input character

	/**
	 * Advances the position (pos) past the first occurrence of the character c.
	 * If c is not found, pos is set to the end of the program string.
	 * This is useful for skipping comments to the end of the line.
	 * @param c The character to skip past.
	 */	
	private void past(char c) {
		while (!done()&&c!=program.charAt(pos))
			pos++;
		if (!done()&&c==program.charAt(pos))
			pos++;
	}

	// scan various kinds of lexeme

	/**
	 * Scans a number literal (a sequence of digits) and creates a "num" token.
	 * This method must be extended to support floating-point numbers (doubles).
	 */	
	// private void nextNumber() {
	// 	int old=pos;
	// 	many(digits);
	// 	// modded to check for doubles
	// 	if (!done() && program.charAt(pos) == '.') {
	// 		pos++; // consume decimal 
	// 		many(digits); // consume remaining digits
	// 	}
	// 	token=new Token("num",program.substring(old,pos));
	// }
	private void nextNumber() {
    int old = pos;
    // Read the integer part (digits)
    while (!done() && digits.contains(program.charAt(pos) + "")) {
        pos++;
    }

    //Check for and read the fractional part (dot and more digits)
    if (!done() && program.charAt(pos) == '.') {
        pos++; // Consume the '.'
        // Read the digits after the decimal point
        while (!done() && digits.contains(program.charAt(pos) + "")) {
            pos++;
        }
    }
    // All numbers are now treated as one token ("1", "1.5", "5.0")
    
    String lexeme = program.substring(old, pos);
    token = new Token("num", lexeme);
}

	/**
	 * Scans a sequence of legitimate characters, determines if it is a keyword (e.g., "rd") 
	 * or an identifier (e.g., "x"), and creates the appropriate token.
	 */	
	private void nextKwId() {
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}

	/**
	 * Scans an operator, handling both single-character and potential two-character operators.
	 */	
	private void nextOp() {
		int old=pos;
		pos=old+2;
		if (!done()) {
			String lexeme=program.substring(old,pos);
			if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
	}

	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
public boolean next() {
    while (true) {
        many(whitespace);
        
        if (done()) {
            token=new Token("EOF");
            return false;
        }
        
        String c = program.charAt(pos) + "";
        
        // Check for and skip comments (e.g., //)
        if (c.equals("/") && pos < program.length() - 1 && program.charAt(pos + 1) == '/') {
            pos += 2; // Consume the '//'
            past('\n'); // Move position past the newline (or to EOF)
            // The 'while (true)' loop continues, and on the next iteration, 
            // it will skip any leading whitespace/comments after the newline.
        } 
        else if (digits.contains(c))
            nextNumber();
        else if (letters.contains(c))
            nextKwId();
        else if (operators.contains(c))
            nextOp();
        else {
            System.err.println("illegal character at position " + pos);
            pos++;
            return next(); // overflow issue?
        }
        
        // If we found a regular token (id, num, op), break the loop and return true.
        if (c.equals("/") && pos < program.length() && program.charAt(pos - 1) == '/') { // double-checking '/' 
             
        } else {
			return true;
		}
    }
}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos,t,curr());
		next();
	}

	public Token curr() throws SyntaxException {
		if (token==null)
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		return token;
	}

	/**
	 * Returns the current character position in the source program.
	 * @return The current position index.
	 */
	public int pos() {
		return pos;
	}

	// for unit testing
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}

}
