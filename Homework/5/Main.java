/**
 * Created by greg on 13/03/2017.
 */
public class Main {
    public static void main(String[] args) {
        Expression substraction = new Substract(
                                    new Variable("x"),
                                    new Const(1)
                                                );
        System.out.println(new Multiply(substraction, substraction).evaluate(Integer.parseInt(args[0])));
    }
}
