package expression.exceptions;

import expression.TripleExpression;
import expression.UnaryOperation;

/**
 * Created by greg on 29/03/2017.
 */
public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int x) throws CalculatingException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Negation overflow error counting -" + x);
        }
        return -x;
    }
}
