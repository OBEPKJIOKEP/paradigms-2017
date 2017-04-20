package expression;

import expression.exceptions.CalculatingException;

/**
 * Created by greg on 29/03/2017.
 */
public abstract class UnaryOperation implements TripleExpression {
    private TripleExpression expression;

    protected UnaryOperation(TripleExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int x, int y, int z) throws CalculatingException {
        return evaluateImpl(expression.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int x) throws CalculatingException;
}
