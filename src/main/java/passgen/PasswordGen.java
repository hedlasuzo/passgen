package passgen;

import java.security.SecureRandom;

public class PasswordGen {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String randomPassword(int len, Chars c) {
        StringBuilder pass = new StringBuilder();
        char[] chars = c.toCharArray();
        for (int i = 0; i < len; i++) {
            pass.append(chars[RANDOM.nextInt(chars.length)]);
        }
        return pass.toString();
    }
}
