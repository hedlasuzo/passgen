package passgen;

public enum Chars {
    lcase(range('a', 'z')),
    ucase(range('A', 'Z')),
    digit(range('0', '9')),
    symbol(range('!', '/') + range(':', '@') + range('[', '`') + range('{', '~')),
    all(range('a', 'z') + range('A', 'Z') + range('0', '9') + range('!', '/') + range(':', '@')
            + range('[', '`') + range('{', '~'));

    private final String chars;

    Chars(String chars) {
        this.chars = chars;
    }

    static String range(char start, char end) {
        assert start < end;
        StringBuilder sb = new StringBuilder(1 + end - start);
        for (char nxt = start; nxt <= end; nxt++) {
            sb.append(nxt);
        }
        return sb.toString();
    }

    public char[] toCharArray() {
        return chars.toCharArray();
    }

}
