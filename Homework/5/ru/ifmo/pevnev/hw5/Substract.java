package ru.ifmo.pevnev.hw5;

/**
 * Created by greg on 13/03/2017.
 */
public class Substract extends BinaryOperation {
    public Substract(Expression left, Expression right) {
        super(left, right);
    }

    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }
}
