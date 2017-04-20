package expression.parser;

/**
 * Created by greg on 11/04/2017.
 */
public class UnsupportedTokenException extends ParsingException {
    public UnsupportedTokenException(Token incorrectToken) {
        super("Unsupported token \"" + incorrectToken.getValue() +
                "\" starting at position " + incorrectToken.getPosition());
    }
}
