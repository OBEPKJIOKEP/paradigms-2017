import expression.parser.ExpressionParser;

/**
 * Created by greg on 27/03/2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new ExpressionParser().parse("2 + (x + 2) * x * y").evaluate(0, 0, 0));
    }
}
