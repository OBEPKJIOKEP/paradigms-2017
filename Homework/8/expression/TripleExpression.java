package expression;

import expression.exceptions.CalculatingException;
import number.Number;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T extends Number<T>> {
    T evaluate(T x, T y, T z) throws CalculatingException;
}
