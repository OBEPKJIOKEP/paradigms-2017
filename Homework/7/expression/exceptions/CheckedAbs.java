package expression.exceptions;

import expression.TripleExpression;
import expression.UnaryOperation;

/**
 * Created by greg on 05/04/2017.
 */
public class CheckedAbs extends UnaryOperation {
    public CheckedAbs(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int x) throws CalculatingException {
        if (x == Integer.MIN_VALUE) {
           throw new OverflowException("Overflow counting abs(" + x + ")");
        }
        return x < 0 ? -x : x;
    }
}
