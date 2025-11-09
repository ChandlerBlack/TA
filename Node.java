// Author - https://github.com/JimBuffenbarger/dws
// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated,
// and/or code()-generated.

public abstract class Node {

	protected int pos=0;

	/**
	 * Interprets the program fragment represented by this AST node.
	 * This method is the heart of the interpreter.
	 * * @param env The environment containing variable mappings.
	 * @return The integer result of evaluating this node (e.g., the value of an expression).
	 * @throws EvalException If a runtime error occurs (e.g., division by zero).
	 */
	public double eval(Environment env) throws EvalException {
		throw new EvalException(pos,"cannot eval() node!");
	}

	/**
	 * Generates the C code for the assignment statement, including the expression evaluation and the side-effect of printing the result.
	 * * @return The C code string (e.g., "x=1+2;printf(...);").
	 */	
	public String code() { return ""; }

}
