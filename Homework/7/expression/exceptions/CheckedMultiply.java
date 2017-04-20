package expression.exceptions;

import expression.BinaryOperation;
import expression.TripleExpression;

/**
 * Created by greg on 29/03/2017.
 */
public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws CalculatingException {
        int result = left * right;
        if ((left != 0 && result / left != right)
                || ( left == -1 && right == Integer.MIN_VALUE)
                || (left == Integer.MIN_VALUE && right == -1)) {
            throw new OverflowException("Overflow counting " + left + " * " + right);
        }
        return result;
    }
}
