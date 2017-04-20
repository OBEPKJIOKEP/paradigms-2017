package number;

import expression.exceptions.OverflowException;

/**
 * Created by greg on 09/04/2017.
 */
public class DoubleNumber implements Number<DoubleNumber> {
    private Double value;

    public DoubleNumber(Integer value) {
        this.value = value.doubleValue();
    }

    public DoubleNumber(Double value) {
        this.value = value;
    }

    public DoubleNumber(String value) throws NumberFormatException {
        this.value = Double.parseDouble(value);
    }

    @Override
    public DoubleNumber add(DoubleNumber term) {
        return new DoubleNumber(value + term.value);
    }

    @Override
    public DoubleNumber subtract(DoubleNumber term) {
        return new DoubleNumber(value - term.value);
    }

    @Override
    public DoubleNumber multiply(DoubleNumber term) {
        return new DoubleNumber(value * term.value);
    }

    @Override
    public DoubleNumber divide(DoubleNumber term) {
        return new DoubleNumber(value / term.value);
    }

    @Override
    public DoubleNumber negate() {
        return new DoubleNumber(-value);
    }

    @Override
    public DoubleNumber abs() throws OverflowException {
        return value < 0 ? negate() : this;
    }

    @Override
    public DoubleNumber square() throws OverflowException {
        return multiply(this);
    }

    @Override
    public DoubleNumber mod(DoubleNumber term) {
        return new DoubleNumber(value % term.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Double getValue() {
        return value;
    }
}
