package expression;


import number.Number;

/**
 * Created by greg on 27/03/2017.
 */
public class Variable<T extends Number<T>> implements TripleExpression<T> {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public T evaluate(T x, T y, T z) {
        if (variable.equals("x")) {
            return x;
        } else if (variable.equals("y")) {
            return y;
        } else {
            return z;
        }
    }


}
