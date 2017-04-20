package number;

/**
 * Created by greg on 09/04/2017.
 */
@FunctionalInterface
public interface NumberParser<T extends Number<T>> {
    T parseNumber(String value);
}
