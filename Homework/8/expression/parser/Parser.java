package expression.parser;

import expression.TripleExpression;
import number.Number;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T extends Number<T>> {
    TripleExpression<T> parse(String expression) throws ParsingException;
}
