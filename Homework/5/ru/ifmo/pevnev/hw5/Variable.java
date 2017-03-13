package ru.ifmo.pevnev.hw5;

/**
 * Created by greg on 13/03/2017.
 */
public class Variable implements Expression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int x) {
        return x;
    }
}
