public class Token {
    private int value;

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (this.value == 0) {
            this.value = value;
        }
    }
}
