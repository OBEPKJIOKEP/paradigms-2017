package expression.parser;

/**
 * Created by greg on 12/04/2017.
 */
public class MissingOperationException extends ParsingException {
    public MissingOperationException(Token extraNumber) {
        super("Operation expected for " + (extraNumber.getType() == TokenType.VARIABLE ?
                                            "variable " :
                                            "number ") +
                                            extraNumber.getValue()
        );
    }
}
