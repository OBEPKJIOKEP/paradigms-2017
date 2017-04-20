package expression;

/**
 * Created by greg on 27/03/2017.
 */
public abstract class BinaryOperation implements CommonExpression {
    private CommonExpression left, right;

    public BinaryOperation(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate(int x) {
        return evaluateImpl(left.evaluate(x), right.evaluate(x));
    }

    public double evaluate(double x) {
        return evaluateImpl(left.evaluate(x), right.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int left, int right);

    protected abstract double evaluateImpl(double left, double right);
}
