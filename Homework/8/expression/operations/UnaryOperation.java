package expression.operations;

import expression.TripleExpression;
import expression.exceptions.*;
import number.Number;

/**
 * Created by greg on 29/03/2017.
 */
public abstract class UnaryOperation<T extends Number<T>> implements TripleExpression<T> {
    private TripleExpression<T> expression;

    protected UnaryOperation(TripleExpression<T> expression) {
        this.expression = expression;
    }

    public T evaluate(T x, T y, T z) throws CalculatingException {

        return evaluateImpl(expression.evaluate(x, y, z));
    }

    protected abstract T evaluateImpl(T x) throws CalculatingException;
}
