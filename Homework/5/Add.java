/**
 * Created by greg on 13/03/2017.
 */
public class Add extends BinaryOperation{
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }
}
