/**
 * Created by greg on 13/03/2017.
 */
public class Multiply extends BinaryOperation {
    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }
}
