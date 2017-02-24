/**
 * Created by Greg on 8.02.2017.
 */
public class Sum {
    public static void main(String[] args) {
        int sum = 0, begin, end;
        for(String arg : args) {
            begin = 0;
            end = 0;
            arg += " ";
            for(int i = 0; i < arg.length(); i++) {
                if(Character.isWhitespace(arg.charAt(i))) {
                    if(end != begin) {
                        sum += Integer.parseInt(arg.substring(begin, end));
                    }
                    begin = i + 1;
                    end = i + 1;
                } else {
                    end++;
                }
            }
        }
        System.out.println(sum);
    }
}