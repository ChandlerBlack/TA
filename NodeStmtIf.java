public class NodeStmtIf extends Node {
    private NodeBoolExpr boolExpr;
    private Node thenStmt;
    private Node elseStmt; // this can be null

    public NodeStmtIf(NodeBoolExpr boolExpr, Node thenStmt, Node elseStmt) {
        this.boolExpr = boolExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public NodeStmtIf(NodeBoolExpr boolExpr, Node thenStmt) {
        this.boolExpr = boolExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = null;
    }

    public double eval(Environment env) throws EvalException {
        double cond = boolExpr.eval(env);
        if (cond != 0.0) {
            return thenStmt.eval(env);
        } else if (elseStmt != null) {
            return elseStmt.eval(env);
        }
        return 0.0;
    }


    public String code() {
        String code = "if (" + boolExpr.code() + ") {\n";
        code += thenStmt.code() + "\n";
        code += "}";
        if (elseStmt != null) {
            code += " else {\n";
            code += elseStmt.code() + "\n";
            code += "}";
        }
        return code;
    }
}
