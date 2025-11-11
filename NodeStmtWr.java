public class NodeStmtWr extends Node {
    private NodeExpr expr;

    public NodeStmtWr(NodeExpr expr) {
        this.expr = expr;
    }

    public double eval(Environment env) throws EvalException{
        double value = expr.eval(env);
        int intVal = (int) value;
        if (intVal == value) {
            System.out.println(intVal);
        } else {
            System.out.println(value);
        }
        return value;

    }
}
