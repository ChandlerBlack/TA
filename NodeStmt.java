// Author - https://github.com/JimBuffenbarger/dws
public class NodeStmt extends Node {

	private NodeAssn assn;

	public NodeStmt(NodeAssn assn) {
		this.assn=assn;
	}

	/**
	 * Interprets the statement, which currently delegates to the embedded assignment statement's evaluation.
	 * @param env The environment for variable storage.
	 * @return The integer result of the assignment evaluation.
	 * @throws EvalException If the assignment evaluation fails.
	 */	
	public double eval(Environment env) throws EvalException {
		return assn.eval(env);
	}

	public String code() { return assn.code(); }

}
