package expression.exceptions;

import expression.TripleExpression;
import expression.UnaryOperation;

/**
 * Created by greg on 05/04/2017.
 */
public class CheckedSqrt extends UnaryOperation {
    public CheckedSqrt(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int x) throws CalculatingException {
        if (x < 0) {
            throw new IllegalArgumentException("Sqrt can't be calculated from " + x );
        }
        int i = 1;
        while (i * i <= x) {
            i++;
        }
        return i - 1;
    }
}
