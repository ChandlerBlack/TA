// Author - https://github.com/JimBuffenbarger/dws
public class NodeAssn extends Node {

	private String id;
	private NodeExpr expr;

	public NodeAssn(String id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}

	/**
	 * Interprets the assignment statement. 
	 * It evaluates the expression, puts the value into the environment, and prints the result.
	 * @param env The environment for variable storage.
	 * @return The value assigned.
	 * @throws EvalException If the expression evaluation fails.
	 */	
	public double eval(Environment env) throws EvalException {
		return env.put(id, new NodeWr(expr).eval(env));
	}

	public String code() {
		return id + "=" + expr.code() + ";" + new NodeWr(expr).code();
	}

}
