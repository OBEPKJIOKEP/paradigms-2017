package number;

import expression.exceptions.CalculatingException;
import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

/**
 * Created by greg on 09/04/2017.
 */
public class IntegerNumber implements Number<IntegerNumber> {
    private Integer value;

    public IntegerNumber(Integer value) {
        this.value = value;
    }

    public IntegerNumber(String value) throws NumberFormatException {
        this.value = Integer.parseInt(value);
    }

    @Override
    public IntegerNumber add(IntegerNumber term) throws OverflowException {
        int result = value + term.value;
        if (((value ^ result) & (term.value ^ result)) < 0) {
            throw new OverflowException("Overflow counting " + this + " + " + term);
        }
        return new IntegerNumber(result);
    }

    @Override
    public IntegerNumber subtract(IntegerNumber term) throws OverflowException {
        int result = value - term.value;
        if (((value ^ term.value) & (value ^ result)) < 0) {
            throw new OverflowException("Underflow counting " + this + " - " + term);
        }
        return new IntegerNumber(result);
    }

    @Override
    public IntegerNumber multiply(IntegerNumber term) throws OverflowException {
        int result = value * term.value;
        if ((value != 0 && result / value != term.value)
                || ( value == -1 && term.value == Integer.MIN_VALUE)
                || (value == Integer.MIN_VALUE && term.value == -1)) {
            throw new OverflowException("Overflow counting " + this + " * " + term);
        }
        return new IntegerNumber(result);
    }

    @Override
    public IntegerNumber divide(IntegerNumber term) throws CalculatingException {
        if (term.value == 0) {
            throw new DivisionByZeroException("Division by zero counting " + this + " / " + term);
        }
        if (value == Integer.MIN_VALUE && term.value == -1) {
            throw new OverflowException("Overflow counting " + this + " / " + term);
        }
        return new IntegerNumber(value / term.value);
    }

    @Override
    public IntegerNumber negate() throws OverflowException{
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("Negation overflow error counting -" + this);
        }
        return new IntegerNumber(-value);
    }

    @Override
    public IntegerNumber abs() throws OverflowException {
        return value < 0 ? negate() : this;
    }

    @Override
    public IntegerNumber square() throws OverflowException {
        return multiply(this);
    }

    @Override
    public IntegerNumber mod(IntegerNumber term) {
        return new IntegerNumber(value % term.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
