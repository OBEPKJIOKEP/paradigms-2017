package expression.operations;

import expression.TripleExpression;
import expression.exceptions.CalculatingException;
import number.Number;

/**
 * Created by greg on 08/04/2017.
 */
public class Negate<T extends Number<T>> extends UnaryOperation<T> {
    public Negate(TripleExpression<T> expression) {
        super(expression);
    }

    @Override
    protected T evaluateImpl(T x) throws CalculatingException {
        return x.negate();
    }
}
