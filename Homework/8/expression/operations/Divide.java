package expression.operations;

import expression.exceptions.CalculatingException;
import number.Number;
import expression.TripleExpression;

/**
 * Created by greg on 08/04/2017.
 */
public class Divide<T extends Number<T>> extends BinaryOperation<T> {
    public Divide(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T evaluateImpl(T left, T right) throws CalculatingException {
        return left.divide(right);
    }
}
