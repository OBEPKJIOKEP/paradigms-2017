package expression.generic;

import expression.TripleExpression;
import expression.exceptions.CalculatingException;
import expression.parser.ExpressionParser;
import expression.parser.ParsingException;
import number.*;
import number.Number;

import java.util.function.Function;

/**
 * Created by greg on 03/04/2017.
 */
public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2)
                throws ParsingException {
        TabulatorImpl tabulator;
        switch (mode) {
            case "i":
                tabulator = new TabulatorImpl<>(IntegerNumber::new, IntegerNumber::new);
                break;
            case "d":
                tabulator = new TabulatorImpl<>(DoubleNumber::new, DoubleNumber::new);
                break;
            case "bi":
                tabulator = new TabulatorImpl<>(BigIntegerNumber::new, BigIntegerNumber::new);
                break;

            default:
                throw new CalculatingException("Unknown calculating mode " + mode);
        }
        return tabulator.tabulateImpl(mode, expression, x1, x2, y1, y2, z1, z2);
    }

    private class TabulatorImpl<T extends Number<T>> {
        private NumberParser<T> parser;
        private Function<Integer, T> converter;

        private TabulatorImpl(Function<Integer, T> converter, NumberParser<T> parser){
            this.converter = converter;
            this.parser = parser;
        }

        private Object[][][] tabulateImpl(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2)
                throws ParsingException {
            int xSize = x2 - x1 + 1;
            int ySize = y2 - y1 + 1;
            int zSize = z2 - z1 + 1;
            Object table[][][] = new Object[xSize][ySize][zSize];
            TripleExpression<T> result = new ExpressionParser<>(parser, mode).parse(expression);
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    for (int k = 0; k < zSize; k++) {
                        try {
                            table[i][j][k] = result.evaluate(converter.apply(x1 + i),
                                    converter.apply(y1 + j),
                                    converter.apply(z1 + k)).
                                    getValue();
                        } catch (Throwable e) {
                            table[i][j][k] = null;
                        }
                    }
                }
            }
            return table;
        }
    }
}
