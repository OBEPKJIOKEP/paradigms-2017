package expression;

/**
 * Created by greg on 29/03/2017.
 */
public class RightShift extends BinaryOperation {
    public RightShift(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) {
        return left >> right;
    }

    @Override
    protected double evaluateImpl(double left, double right) {
        return 0;
    }
}
