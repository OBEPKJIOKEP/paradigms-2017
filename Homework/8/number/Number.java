package number;

import expression.exceptions.CalculatingException;
import expression.exceptions.OverflowException;

/**
 * Created by greg on 04/04/2017.
 */
public interface Number<T> {
    T add(T term) throws OverflowException;

    T subtract(T term) throws OverflowException;

    T multiply(T term) throws OverflowException;

    T divide(T term) throws CalculatingException;

    T negate() throws OverflowException;

    T abs() throws OverflowException;

    T square() throws OverflowException;

    T mod(T term);

    Object getValue();
}
