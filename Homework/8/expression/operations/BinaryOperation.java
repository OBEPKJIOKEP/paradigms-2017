package expression.operations;

import expression.TripleExpression;
import expression.exceptions.CalculatingException;
import number.Number;

/**
 * Created by greg on 27/03/2017.
 */
public abstract class BinaryOperation<T extends Number<T>> implements TripleExpression<T> {
    private TripleExpression<T> left, right;

    public BinaryOperation(TripleExpression<T> left, TripleExpression<T> right) {
        this.left = left;
        this.right = right;
    }

    public T evaluate(T x, T y, T z) throws CalculatingException {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract T evaluateImpl(T left, T right) throws CalculatingException;
}
