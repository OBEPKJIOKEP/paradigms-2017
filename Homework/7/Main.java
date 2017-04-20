import expression.TripleExpression;
import expression.exceptions.CalculatingException;
import expression.parser.ExpressionParser;
import expression.parser.ParsingException;

import java.io.IOException;

/**
 * Created by greg on 27/03/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        TripleExpression result;
        try {
            result = new ExpressionParser().parse(args[0]);
        } catch (ParsingException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("x\t\tf");
        for(int i = 0; i < 11; i++) {
            System.out.print(i + "\t\t");
            try {
                System.out.println(result.evaluate(i, 0, 0));
            } catch (CalculatingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
