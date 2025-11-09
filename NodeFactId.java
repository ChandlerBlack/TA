// Author - https://github.com/JimBuffenbarger/dws
public class NodeFactId extends NodeFact {

	private String id;

	public NodeFactId(int pos, String id) {
		this.pos=pos;
		this.id=id;
	}

	/**
	 * Interprets a factor that is an identifier. It retrieves the variable's value from the environment.
	 * * @param env The environment for variable lookups.
	 * @return The integer value of the variable.
	 * @throws EvalException If the variable is undefined.
	 */	
	public double eval(Environment env) throws EvalException {
		return env.get(pos,id);
	}

	public String code() { return id; }

}
