import expression.generic.GenericTabulator;
import expression.generic.Tabulator;

public class Main {
    public static void main(String[] args) throws Exception {
        Tabulator expressionTabulator = new GenericTabulator();
        Object[][][] result = expressionTabulator.tabulate("i", "mod (z + 1)", -15, 7, -18, 10, -6, 10);
    }
}
