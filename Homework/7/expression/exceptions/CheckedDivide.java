package expression.exceptions;

import expression.BinaryOperation;
import expression.TripleExpression;

/**
 * Created by greg on 29/03/2017.
 */
public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws CalculatingException {
        if (right == 0) {
            throw new DivisionByZeroException("Division by zero counting " + left + " / " + right);
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException("Overflow counting " + left + " / " + right);
        }
        return (left / right);
    }
}
