import java.util.ArrayList;

public class NodeBlock extends Node {
    private ArrayList<Node> stmts;

    public NodeBlock() {
        this.stmts = new ArrayList<>();
    }

    public void add(Node stmt) {
        this.stmts.add(stmt);
    }

    public double eval(Environment env) throws EvalException {
        double lastVal = 0.0;
        for (Node stmt : stmts) {
            lastVal = stmt.eval(env);
        }
        return lastVal;
    }

    public String code() {
        String result = "";
        for (Node stmt : stmts) {
            result += stmt.code() + "\n";
        }
        return result;
    }

 }
