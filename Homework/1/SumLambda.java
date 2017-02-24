
import java.util.Arrays;

/**
 * Created by Greg on 8.02.2017.
 */
class SumLambda {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(args).flatMap((s)->
                Arrays.stream(s.split("\\p{javaWhitespace}+"))).
                        filter((str) -> !str.isEmpty()).
                        mapToInt(Integer::valueOf).sum());
    }
}
