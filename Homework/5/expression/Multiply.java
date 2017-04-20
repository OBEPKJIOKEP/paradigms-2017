package expression;

/**
 * Created by greg on 27/03/2017.
 */
public class Multiply extends BinaryOperation {
    public Multiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int evaluateImpl(int left, int right) {
        return left * right;
    }

    protected double evaluateImpl(double left, double right) {
        return left * right;
    }
}
