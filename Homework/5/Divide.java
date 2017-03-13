/**
 * Created by greg on 13/03/2017.
 */
public class Divide extends BinaryOperation {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }
}
