package number;

import expression.exceptions.OverflowException;

import java.math.BigInteger;

/**
 * Created by greg on 04/04/2017.
 */
public class BigIntegerNumber implements Number<BigIntegerNumber> {
    private BigInteger value;

    public BigIntegerNumber(Integer value) {
        this.value = new BigInteger(value.toString());
    }

    public BigIntegerNumber(BigInteger value) {
        this.value = value;
    }

    public BigIntegerNumber(String value) throws NumberFormatException {
        this.value = new BigInteger(value);
    }

    @Override
    public BigIntegerNumber add(BigIntegerNumber term) {
        return new BigIntegerNumber(value.add(term.value));
    }

    @Override
    public BigIntegerNumber subtract(BigIntegerNumber term) {
        return new BigIntegerNumber(value.subtract(term.value));
    }

    @Override
    public BigIntegerNumber multiply(BigIntegerNumber term) {
        return new BigIntegerNumber(value.multiply(term.value));
    }

    @Override
    public BigIntegerNumber divide(BigIntegerNumber term) {
        return new BigIntegerNumber(value.divide(term.value));
    }

    @Override
    public BigIntegerNumber negate() {
        return new BigIntegerNumber(value.negate());
    }

    @Override
    public BigIntegerNumber abs() throws OverflowException {

        return value.compareTo(BigInteger.ZERO) < 0 ? negate() : this;
    }

    @Override
    public BigIntegerNumber square() throws OverflowException {
        return multiply(this);
    }

    @Override
    public BigIntegerNumber mod(BigIntegerNumber term) {
        return new BigIntegerNumber(value.remainder(term.value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public BigInteger getValue() {
        return value;
    }
}