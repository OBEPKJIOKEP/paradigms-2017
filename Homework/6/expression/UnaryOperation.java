package expression;

/**
 * Created by greg on 29/03/2017.
 */
public abstract class UnaryOperation implements CommonExpression {
    private CommonExpression expression;

    protected UnaryOperation(CommonExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int x) {
        return evaluateImpl(expression.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return evaluateImpl(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateImpl(expression.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int x);

    protected abstract double evaluateImpl(double x);
}
