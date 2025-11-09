// Author - https://github.com/JimBuffenbarger/dws
public class NodeFactNum extends NodeFact {

	private String num;

	public NodeFactNum(String num) {
		this.num=num;
	}

	/**
	 * Interprets a factor that is a number literal. It converts the string lexeme into an integer value.
	 * @param env The environment (unused for literals).
	 * @return The integer value of the number.
	 * @throws EvalException (Will only occur if Integer.parseInt fails, though typically handled by Scanner/Parser).
	 */	
	public double eval(Environment env) throws EvalException {
		return Double.parseDouble(num);
	}

	public String code() { return num; }

}
