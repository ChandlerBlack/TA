public class NodeStmtWhile extends Node {
    private NodeBoolExpr boolExpr;
    private Node stmt;

    public NodeStmtWhile(NodeBoolExpr boolExpr, Node stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }

    public double eval(Environment env) throws EvalException {
        double lastVal = 0.0;
        while (boolExpr.eval(env) != 0.0) {
            lastVal = stmt.eval(env);
        }
        return lastVal;
    } 

    public String code() {
        return "while (" + boolExpr.code() + ") {\n" + 
               stmt.code() + "\n" +
               "}";
    }
}
