package expression.exceptions;

/**
 * Created by greg on 29/03/2017.
 */
public class DivisionByZeroException extends CalculatingException {
    public DivisionByZeroException(String message) {
        super(message);
    }

}
