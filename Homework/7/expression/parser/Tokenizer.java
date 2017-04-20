package expression.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 08/04/2017.
 */
public class Tokenizer {
    public static List<Token> tokenize(String expression) {
        int currentPosition = 0;
        expression += '\0';
        List<Token> tokens = new ArrayList<>();
        while (expression.charAt(currentPosition) != '\0') {
            char c = expression.charAt(currentPosition);
            if (Character.isWhitespace(c)) {
                currentPosition++;
                continue;
            }
            if (c == '(' || c == ')') {
                tokens.add(new Token("" + c, currentPosition + 1, TokenType.BRACKET));
                currentPosition++;
                continue;
            }
            if (Character.isLetter(c)) {
                int begin = currentPosition;
                if ("xyz".contains("" + c)) {
                    tokens.add(new Token("" + c, begin + 1, TokenType.VARIABLE));
                    currentPosition++;
                } else {
                    while (Character.isLetterOrDigit(c)) {
                        c = expression.charAt(++currentPosition);
                    }
                    tokens.add(new Token(expression.substring(begin, currentPosition), begin + 1, TokenType.WORD));
                }
                continue;
            }
            if (Character.isDigit(c)) {
                int begin = currentPosition;
                while (Character.isDigit(c)) {
                    c = expression.charAt(++currentPosition);
                }
                tokens.add(new Token(expression.substring(begin, currentPosition), begin + 1, TokenType.NUMBER));
                continue;
            }
            if("+-*/".contains("" + c)) {
                tokens.add(new Token("" + c, currentPosition + 1, TokenType.OPERATION));
                currentPosition++;
                continue;
            }
            int begin = currentPosition;
            while (!Character.isWhitespace(c) && !Character.isDigit(c) && !Character.isAlphabetic(c) && !"+-*/()".contains("" + c) && c != '\0') {
                c = expression.charAt(++currentPosition);
            }
            tokens.add(new Token(expression.substring(begin, currentPosition), begin + 1, TokenType.WORD));
        }
        return tokens;
    }
}
