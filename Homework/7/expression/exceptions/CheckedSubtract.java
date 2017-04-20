package expression.exceptions;

import expression.BinaryOperation;
import expression.TripleExpression;

/**
 * Created by greg on 27/03/2017.
 */
public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    protected int evaluateImpl(int left, int right) throws CalculatingException {
        int result = left - right;
        if (((left ^ right) & (left ^ result)) < 0) {
            throw new OverflowException("Underflow counting " + left + " - " + right);
        }
        return result;
    }
}
