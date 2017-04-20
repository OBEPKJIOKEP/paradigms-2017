package expression;

/**
 * Created by greg on 27/03/2017.
 */
public class Negate extends UnaryOperation{
    public Negate(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int x) {
        return -x;
    }

    @Override
    protected double evaluateImpl(double x) {
        return -x;
    }
}
