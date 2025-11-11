public class NodeRelop extends Node {
    private String relop;

    // constructor
    public NodeRelop(int pos, String relop) {
        this.pos = pos;
        this.relop = relop;
    }

    /**
     * Compares two operands based on the relational operator defined by this node.
     * @param o1 The first operand.
     * @param o2 The second operand.
     * @return The result of the comparison o1 <relop> o2.
     * @throws EvalException If the operator is unrecognized (bogus).
     */

    public double op(double o1, double o2) throws EvalException {
        if (relop.equals("<")) {
            return o1 < o2 ? 1.0 : 0.0;
        }
        if (relop.equals(">")) {
            return o1 > o2 ? 1.0 : 0.0;
        }
        if (relop.equals("<=")) {
            return o1 <= o2 ? 1.0 : 0.0;
        }
        if (relop.equals(">=")) {
            return o1 >= o2 ? 1.0 : 0.0;
        }
        if (relop.equals("==")) {
            return o1 == o2 ? 1.0 : 0.0;
        }
        if (relop.equals("<>")) {
            return o1 != o2 ? 1.0 : 0.0;
        }
        throw new EvalException(pos, "bogus relop: " + relop);
    }

    public String code() {
        if (relop.equals("<>")) {
            return "!="; // because != is not equals in C
        }
        return relop;
    }
}
