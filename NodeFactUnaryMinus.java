// Author - ChandlerBlack
public class NodeFactUnaryMinus extends NodeFact {

    private NodeFact fact;

    public NodeFactUnaryMinus(NodeFact fact) {
        this.fact = fact;
    }

    public double eval(Environment env) throws EvalException {
        return -fact.eval(env); // negate the result
    }

    public String code () {
        return "-(" + fact.code() + ")";
    }
    
}
