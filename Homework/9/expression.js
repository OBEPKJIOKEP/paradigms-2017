/**
 * Created by greg on 18/04/2017.
 */
var expr = subtract(variable("x"), cnst(1));
expr = multiply(expr, expr);
for (var i = 0; i <= 10; i++) {
    println(expr(i));
}
