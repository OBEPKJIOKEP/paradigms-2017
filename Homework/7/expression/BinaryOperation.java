package expression;

import expression.exceptions.CalculatingException;

/**
 * Created by greg on 27/03/2017.
 */
public abstract class BinaryOperation implements TripleExpression {
    private TripleExpression left, right;

    public BinaryOperation(TripleExpression left, TripleExpression right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate(int x, int y, int z) throws CalculatingException {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int left, int right) throws CalculatingException;
}
