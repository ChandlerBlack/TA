// Author - https://github.com/JimBuffenbarger/dws
public class NodeTerm extends Node {

	private NodeFact fact;
	private NodeMulop mulop;
	private NodeTerm term;

	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
	}

	/**
	 * Appends a new fact/mulop pair to this term node, 
	 * maintaining the right-recursive structure of the AST for multiplication/division.
	 * * @param term The term node containing the new factor and mulop.
	 */	
	public void append(NodeTerm term) {
		if (this.term==null) {
			this.mulop=term.mulop;
			this.term=term;
			term.mulop=null;
		} else
			this.term.append(term);
	}

	/**
	 * Interprets the term. If this node represents a chain (fact * term), 
	 * it evaluates the right-hand side first (term) and applies the mulop to the factor.
	 * @param env The environment for variable lookups.
	 * @return The integer result of the term evaluation.
	 * @throws EvalException If an error occurs during arithmetic (e.g., division by zero).
	 */	
	public double eval(Environment env) throws EvalException {
		return term==null
			? fact.eval(env)
			: mulop.op(term.eval(env),fact.eval(env));
	}

	public String code() {
		return (term==null ? "" : term.code()+mulop.code())+fact.code();
	}

}
