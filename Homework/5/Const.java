/**
 * Created by greg on 13/03/2017.
 */
public class Const implements Expression {

    private int constant;
    public Const(int constant) {
        this.constant = constant;
    }

    public int evaluate(int x) {
        return constant;
    }
}
