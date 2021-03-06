package expression.operations;

import expression.TripleExpression;
import expression.exceptions.CalculatingException;
import number.Number;

/**
 * Created by greg on 11/04/2017.
 */
public class Square<T extends Number<T>> extends UnaryOperation<T> {
    public Square(TripleExpression<T> expression) {
        super(expression);
    }

    @Override
    protected T evaluateImpl(T x) throws CalculatingException {
        return x.square();
    }
}
