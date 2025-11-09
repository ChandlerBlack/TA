// Author - https://github.com/JimBuffenbarger/dws
public class NodeExpr extends Node {

	private NodeTerm term;
	private NodeAddop addop;
	private NodeExpr expr;

	public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
		this.term=term;
		this.addop=addop;
		this.expr=expr;
	}

	/**
	 * Appends a new term/addop pair to this expression node, 
	 * maintaining the right-recursive structure of the AST for addition/subtraction.
	 * @param expr The expression node containing the new term and addop.
	 */	
	public void append(NodeExpr expr) {
		if (this.expr==null) {
			this.addop=expr.addop;
			this.expr=expr;
			expr.addop=null;
		} else
			this.expr.append(expr);
	}

	/**
	 * Interprets the expression. If this node represents a chain (term + expr), 
	 * it evaluates the right-hand side first (expr) and applies the addop to the term.
	 * @param env The environment for variable lookups.
	 * @return The integer result of the expression evaluation.
	 * @throws EvalException If an error occurs during arithmetic.
	 */	
	public double eval(Environment env) throws EvalException {
		return expr==null
			? term.eval(env)
			: addop.op(expr.eval(env),term.eval(env));
	}

	public String code() {
		return (expr==null ? "" : expr.code()+addop.code())+term.code();
	}

}
