import java.util.Scanner;

public class NodeStmtRd extends Node {

    private String id;
    
    public NodeStmtRd(int pos, String id) {
        this.pos = pos;
        this.id = id;
    }

    public double eval(Environment env) throws EvalException {
        Scanner scanner = new Scanner(System.in);
        double value = scanner.nextDouble();
        return env.put(id, value);
    }

    public String code() {
        return "scanf(\"%lf\", &" + id + ");";
    }
}