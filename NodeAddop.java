// Author - https://github.com/JimBuffenbarger/dws
public class NodeAddop extends Node {

	private String addop;

	public NodeAddop(int pos, String addop) {
		this.pos=pos;
		this.addop=addop;
	}

	/**
	 * Performs the arithmetic operation defined by this addop node.
	 * * @param o1 The first operand.
	 * @param o2 The second operand.
	 * @return The result of o1 <addop> o2.
	 * @throws EvalException If the operator is unrecognized (bogus).
	 */	
	public double op(double o1, double o2) throws EvalException {
		if (addop.equals("+"))
			return o1+o2;
		if (addop.equals("-"))
			return o1-o2;
		throw new EvalException(pos,"bogus addop: "+addop);
	}

	public String code() { return addop; }

}
