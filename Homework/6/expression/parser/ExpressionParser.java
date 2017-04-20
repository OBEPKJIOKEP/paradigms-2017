package expression.parser;

import expression.*;

/**
 * Created by greg on 27/03/2017.
 */
public class ExpressionParser implements Parser {
    private int current;
    private String rest;

    public CommonExpression parse(String expression) {
        current = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isWhitespace(expression.charAt(i))) {
                sb.append(expression.charAt(i));
            }
        }
        rest = sb.toString() + "\0\0\0\0\0";
        return parseShifts();
    }

    private boolean test(char ch) {
        boolean result = rest.charAt(current) == ch;
        if (result) {
            current++;
        }
        return result;
    }

    private boolean test(String testing) {
        boolean result = rest.startsWith(testing, current);
        if (result) {
            current += testing.length();
        }
        return result;
    }

    private CommonExpression parseShifts() {
        CommonExpression left = parseAddSub();
        while (test("<<") || test(">>")) {
            if (rest.substring(current - 2, current).equals("<<")) {
                left = new LeftShift(left, parseAddSub());
            } else {
                left = new RightShift(left, parseAddSub());
            }
        }
        return left;
    }

    private CommonExpression parseAddSub() {
        CommonExpression left = parseMulDiv();
        while (test('+') || test('-')) {
            if (rest.charAt(current - 1) == '+') {
                left = new Add(left, parseMulDiv());
            } else {
                left = new Subtract(left, parseMulDiv());
            }
        }
        return left;
    }

    private CommonExpression parseMulDiv() {
        CommonExpression left = parseUnary(), right;
        while (test('*') || test('/')) {
            if (rest.charAt(current - 1) == '*') {
                left = new Multiply(left, parseUnary());
            } else {
                left = new Divide(left, parseUnary());
            }
        }
        return left;
    }

    private CommonExpression parseUnary() {
        if (test('-')) {
            return new Negate(parseUnary());
        }
        if (test("abs")) {
            return new Absolute(parseUnary());
        }
        if (test("square")) {
            return new Square(parseUnary());
        }
        if (test('(')) {
            CommonExpression result = parseShifts();
            current++;
            return result;
        }
        return parseConstOrVariable();
    }

    private CommonExpression parseConstOrVariable() {
        char firstChar = rest.charAt(current);
        if (Character.isAlphabetic(firstChar)) {
            current++;
            return new Variable("" + firstChar);
        }
        int firstIndex = current;
        while (Character.isDigit(rest.charAt(current))) {
            current++;
        }
        int constant = Integer.parseUnsignedInt(rest.substring(firstIndex, current));
        return new Const(constant);
    }
}
