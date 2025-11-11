public class NodeBoolExpr extends Node {
    private NodeExpr left;
    private NodeRelop relop;
    private NodeExpr right;

    // constructor
    public NodeBoolExpr(NodeExpr left, NodeRelop relop, NodeExpr right) {
        this.left = left;
        this.relop = relop;
        this.right = right;
    }

    public double eval(Environment env) throws EvalException {
        double leftVal = left.eval(env);
        double rightVal = right.eval(env);
        return relop.op(leftVal, rightVal);

    }


    public String code() {
        return left.code() + relop.code() + right.code();
    }



}
