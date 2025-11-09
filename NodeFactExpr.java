// Author - https://github.com/JimBuffenbarger/dws
public class NodeFactExpr extends NodeFact {

	private NodeExpr expr;

	public NodeFactExpr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Interprets a factor that is a parenthesized expression. It evaluates the inner expression.
	 * * @param env The environment for variable lookups.
	 * @return The integer result of the inner expression.
	 * @throws EvalException If the inner expression evaluation fails.
	 */	
	public double eval(Environment env) throws EvalException {
		return expr.eval(env);
	}

	public String code() { return "("+expr.code()+")"; }

}
