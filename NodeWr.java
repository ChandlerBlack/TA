// Author - https://github.com/JimBuffenbarger/dws
public class NodeWr extends Node {

	private NodeExpr expr;

	public NodeWr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Interprets the write-expression logic. It evaluates the expression and prints its value to System.out.
	 * @param env The environment for variable lookups.
	 * @return The evaluated value of the expression.
	 * @throws EvalException If the expression evaluation fails.
	 */	
	public double eval(Environment env) throws EvalException {
		double d=expr.eval(env);
		int i=(int) d;
		if (i==d)
			System.out.println(i);
		else
			System.out.println(d);
		return d;
	}

	public String code() {
		return "printf(\"%g\\n\","
			+"(double)("
			+expr.code()
			+"));";
	}

}
