package expression.exceptions;

import expression.BinaryOperation;
import expression.TripleExpression;

/**
 * Created by greg on 27/03/2017.
 */
public class CheckedAdd extends BinaryOperation {
    public CheckedAdd(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    protected int evaluateImpl(int left, int right) throws CalculatingException {
        int result = left + right;
        if (((left ^ result) & (right ^ result)) < 0) {
            throw new OverflowException("Overflow counting " + left + " + " + right);
        }
        return result;
    }
}
