/**
 * Created by greg on 13/03/2017.
 */
public abstract class BinaryOperation implements Expression {
    protected Expression left, right;

    protected BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
