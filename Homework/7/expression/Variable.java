package expression;

/**
 * Created by greg on 27/03/2017.
 */
public class Variable implements TripleExpression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int x, int y, int z) {
        if (variable.equals("x")) {
            return x;
        } else if (variable.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
