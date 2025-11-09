public class SyntaxException extends Exception {

	private int pos;
	private Token expected;
	private Token found;

	/**
	 * Constructs a SyntaxException, used to signal an error 
	 * encountered during parsing (syntactic analysis).
	 * @param pos The position in the source program where the error occurred.
	 * @param expected The Token that the parser was expecting.
	 * @param found The Token that was actually found.
	 */	
	public SyntaxException(int pos, Token expected, Token found) {
		this.pos=pos;
		this.expected=expected;
		this.found=found;
	}

	public String toString() {
		return "syntax error"
			+", pos="+pos
			+", expected="+expected
			+", found="+found;
	}

}
