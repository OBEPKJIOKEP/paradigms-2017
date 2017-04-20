package expression;

/**
 * Created by greg on 27/03/2017.
 */
public class Const implements TripleExpression {
    private int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    public int evaluate(int x, int y, int z) {
        return constant;
    }
}
