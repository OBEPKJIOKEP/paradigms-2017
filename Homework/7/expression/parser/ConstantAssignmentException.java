package expression.parser;

/**
 * Created by greg on 11/04/2017.
 */
public class ConstantAssignmentException extends ParsingException {
    public ConstantAssignmentException(int firstPosition, String number) {
        super("Constant \"" + number +"\" starting at position " + firstPosition
                + " can't fit in integer");
    }
}
