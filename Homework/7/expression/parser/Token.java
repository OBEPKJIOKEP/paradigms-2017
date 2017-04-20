package expression.parser;

/**
 * Created by greg on 08/04/2017.
 */
public class Token {
    private String value;
    private TokenType type;
    private int position;

    public Token(String value, int position, TokenType type) {
        this.value = value;
        this.type = type;
        this.position = position;
    }

    public TokenType getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
