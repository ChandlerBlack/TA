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
	 * It evaluates the expression, puts the value into the environment
	 * @param env The environment for variable storage.
	 * @return The value assigned.
	 * @throws EvalException If the expression evaluation fails.
	 */	
	public double eval(Environment env) throws EvalException {
		// return env.put(id, new NodeWr(expr).eval(env));
		double val = expr.eval(env);
		return env.put(id,val);
	}

	public String code() {
		// return id + "=" + expr.code() + ";" + new NodeWr(expr).code();
		return id + " = " + expr.code() + ";";
	}

}
