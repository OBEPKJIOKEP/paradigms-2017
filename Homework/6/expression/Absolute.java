package expression;

import expression.CommonExpression;

/**
 * Created by greg on 29/03/2017.
 */
public class Absolute extends UnaryOperation {
    public Absolute(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int x) {
        return Math.abs(x);
    }

    @Override
    protected double evaluateImpl(double x) {
        return Math.abs(x);
    }
}
