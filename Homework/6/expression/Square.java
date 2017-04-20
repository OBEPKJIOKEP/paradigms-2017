package expression;

/**
 * Created by greg on 29/03/2017.
 */
public class Square extends UnaryOperation {
    public Square(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int x) {
        return x * x;
    }

    @Override
    protected double evaluateImpl(double x) {
        return x * x;
    }
}
