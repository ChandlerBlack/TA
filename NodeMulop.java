// Author - https://github.com/JimBuffenbarger/dws
public class NodeMulop extends Node {

	private String mulop;

	public NodeMulop(int pos, String mulop) {
		this.pos=pos;
		this.mulop=mulop;
	}
	/**
	 * Performs the arithmetic operation defined by this mulop node.
	 * @param o1 The first operand.
	 * @param o2 The second operand.
	 * @return The result of o1 <mulop> o2.
	 * @throws EvalException If the operator is unrecognized (bogus).
	 */
	public double op(double o1, double o2) throws EvalException {
		if (mulop.equals("*"))
			return o1*o2;
		if (mulop.equals("/"))
			return o1/o2;
		throw new EvalException(pos,"bogus mulop: "+mulop);
	}

	public String code() { return mulop; }

}
