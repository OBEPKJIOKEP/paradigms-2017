package expression.operations;

import expression.TripleExpression;
import expression.exceptions.CalculatingException;
import number.Number;

/**
 * Created by greg on 08/04/2017.
 */
public class Multiply<T extends Number<T>> extends BinaryOperation<T>{
    public Multiply(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T evaluateImpl(T left, T right) throws CalculatingException {
        return left.multiply(right);
    }
}
