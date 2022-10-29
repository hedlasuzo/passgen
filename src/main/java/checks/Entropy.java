package checks;

import passgen.Chars;

public class Entropy {
    /**
     * Calculate entropy, in bits, based on the number of possible passwords
     * that can be generated from the specified values (length and character range/s)
     */
    public static int calc(int len, Chars chars) {
        return (int) Math.floor(len * Math.log(chars.toCharArray().length) / Math.log(2));
    }
}
