package expression;

import expression.exceptions.CalculatingException;
import number.Number;

/**
 * Created by greg on 09/04/2017.
 */
public class Const<T extends Number<T>> implements TripleExpression<T> {
    private T constant;

    public Const(T constant) {
        this.constant = constant;
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalculatingException {
        return constant;
    }
}
