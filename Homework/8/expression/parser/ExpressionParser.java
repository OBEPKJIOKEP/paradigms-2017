package expression.parser;

import expression.*;
import expression.operations.*;
import number.Number;
import number.NumberParser;

import java.util.List;

/**
 * Created by greg on 27/03/2017.
 */
public class ExpressionParser<T extends Number<T>> implements Parser<T> {
    private int current;
    private String calculatingType;
    private List<Token> tokens;
    private NumberParser<T> parser;

    public ExpressionParser(NumberParser<T> parser, String calculatingType) {
        this.parser = parser;
        this.calculatingType = calculatingType;
    }

    public TripleExpression<T> parse(String expression) throws ParsingException {
        tokens = Tokenizer.tokenize(expression);
        tokens.add(new Token("", expression.length(), TokenType.EOL));
        current = 0;
        TripleExpression<T> result = parseAddSub();
        if (tokens.get(current).getType() != TokenType.EOL) {
            Token extraToken = tokens.get(current);
            if (extraToken.getValue().equals(")")) {
                throw new IncorrectParenthesisException("Extra", extraToken);
            } else {
                throw new UnsupportedTokenException(extraToken);
            }
        }
        return result;
    }

    private TripleExpression<T> parseAddSub() throws ParsingException{
        TripleExpression<T> left = parseMulDiv();
        while (test("+") || test("-")) {
            if (test(current - 1, "+")) {
                left = new Add<>(left, parseMulDiv());
            } else {
                left = new Subtract<>(left, parseMulDiv());
            }
        }
        return left;
    }

    private TripleExpression<T> parseMulDiv() throws ParsingException{
        TripleExpression<T> left = parseUnary();
        while (test("*") || test("/") || test("mod")) {
            if (test(current - 1, "*")) {
                left = new Multiply<>(left, parseUnary());
            }
            if (test(current - 1, "/")) {
                left = new Divide<>(left, parseUnary());
            }
            if(test(current - 1, "mod")) {
                left = new Module<>(left, parseUnary());
            }
        }
        return left;
    }

    private TripleExpression<T> parseUnary() throws ParsingException{
        if (test("-")) {
            if (test(TokenType.NUMBER)) {
                current -= 2;
                return parseConstOrVariable();
            } else {
                return new Negate<>(parseUnary());
            }
        }
        if (test("abs")) {
            return new Absolute<>(parseUnary());
        }
        if(test("square")) {
            return new Square<>(parseUnary());
        }
        if (test("(")) {
            if (test(")")) {
                throw new IncorrectParenthesisException(tokens.get(current - 2), tokens.get(current - 1));
            }
            Token openingBracket = tokens.get(current - 1);
            TripleExpression<T> result = parseAddSub();
            if (!test(")")) {
                if(test(TokenType.EOL)) {
                    throw new IncorrectParenthesisException("Missing", openingBracket);
                } else {
                    throw new UnsupportedTokenException(tokens.get(current));
                }
            }
            return result;
        }
        if ( current > 0 && test(current - 1, TokenType.WORD) &&
                !test(current, TokenType.NUMBER) &&
                !test(current, TokenType.VARIABLE)) {
            throw new MissingArgumentException("Missing argument for function", tokens.get(current - 1));
        }
        return parseConstOrVariable();
    }

    private TripleExpression<T> parseConstOrVariable() throws ParsingException {
        if (test("x") || test("y") || test("z")) {
            return new Variable<>(getValue(current - 1));
        }
        String number = null;
        int firstPosition = -1;
        if (test("-")) {
            firstPosition = getPosition(current - 1);
            number += "-";
        }
        if (test(TokenType.NUMBER)) {
            number += getValue(current - 1);
            try {
                if (firstPosition == -1) {
                    firstPosition = getPosition(current - 1);
                }
                return new Const<>(parser.parseNumber(number));
            } catch (NumberFormatException e) {
                throw new ConstantAssignmentException(firstPosition, number, calculatingType);
            }
        }
        if ((current == 0  || test(current - 1, "(")) && test(TokenType.OPERATION)) {
            throw new MissingArgumentException("Missing first operand for operation", tokens.get(current - 1));
        }
        if (current > 0 && test(current - 1, TokenType.OPERATION) && test(TokenType.OPERATION)) {
            throw new MissingArgumentException(tokens.get(current - 2), tokens.get(current - 1));
        }
        if (test(")") || test(TokenType.EOL)) {
            throw new MissingArgumentException("Missing last operand for operation", tokens.get(current - 2));
        }
        if (test(TokenType.WORD)) {
            throw new UnsupportedTokenException(tokens.get(current - 1));
        }
        throw new ParsingException("Parser failed, please contact developer");
    }

    private String getValue(int current) {
        return tokens.get(current).getValue();
    }

    private int getPosition(int current) {
        return tokens.get(current).getPosition();
    }

    private boolean test(TokenType testing) {
        boolean result = testing == tokens.get(current).getType();
        if (result) {
            current++;
        }
        return result;
    }

    private boolean test(String testing) {
        boolean result = testing.equals(tokens.get(current).getValue());
        if (result) {
            current++;
        }
        return result;
    }

    private boolean test(int index, String testing) {
        return testing.equals(tokens.get(index).getValue());
    }

    private boolean test(int index, TokenType testing) {
        return testing == tokens.get(index).getType();
    }
}
