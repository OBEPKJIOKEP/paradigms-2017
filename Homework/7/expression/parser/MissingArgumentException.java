package expression.parser;

/**
 * Created by greg on 11/04/2017.
 */
public class MissingArgumentException extends ParsingException {
    public MissingArgumentException(String messageStart, Token operation) {
        super(messageStart + " \"" +
                operation.getValue() + "\" starting at position " + operation.getPosition());
    }

    public MissingArgumentException(Token leftOperation, Token rightOperation) {
        super("Missing middle operand for operations \"" +
                leftOperation.getValue() + "\" and \"" + rightOperation.getValue() + "\"" +
                " starting at positions " + leftOperation.getPosition() + " and " +
                rightOperation.getPosition());
    }
}
