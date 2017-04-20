package expression.parser;

/**
 * Created by greg on 18/04/2017.
 */
public class IncorrectParenthesisException extends ParsingException {
    public IncorrectParenthesisException(Token first, Token last) {
        super("Expected expression or constant between brackets at positions " +
                first.getPosition() + " and " + last.getPosition());
    }

    public IncorrectParenthesisException(String exceptionType, Token bracket) {
        super((exceptionType.equals("Extra") ? "Extra parenthesis at position " :
                "Not found closing parenthesis for parenthesis opened at position ") + bracket.getPosition());
    }
}
