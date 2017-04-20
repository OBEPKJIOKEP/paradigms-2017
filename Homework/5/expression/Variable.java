package expression;

/**
 * Created by greg on 27/03/2017.
 */
public class Variable implements CommonExpression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        switch (variable){
            case "x":
                return x;

            case "y":
                return y;

            case "z":
                return z;
        }
        return 0;
    }
}
