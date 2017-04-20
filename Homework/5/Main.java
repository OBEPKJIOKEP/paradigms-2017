import expression.*;

/**
 * Created by greg on 13/03/2017.
 */
public class Main {
    public static void main(String[] args) {
        CommonExpression subtraction = new Subtract(
                                    new Variable("x"),
                                    new Const(1)
                                                );
        System.out.println(new Multiply(subtraction, subtraction).evaluate(Integer.parseInt(args[0])));
    }
}
