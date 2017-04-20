package expression;

/**
 * Created by greg on 27/03/2017.
 */
public class Const implements CommonExpression {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    public int evaluate(int x) {
        return (int) value;
    }

    public double evaluate(double x) {
        return value;
    }

    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}
