import checks.HIBPCheck;
import passgen.Chars;
import passgen.PasswordGen;
import utils.SHA1;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        demo(2, Chars.all);
        demo(4, Chars.digit);
        demo(4, Chars.lcase);
        demo(8, Chars.mixed);
        demo(20, Chars.all);
    }

    public static void demo(int len, Chars chars) throws IOException, NoSuchAlgorithmException {
        String password = PasswordGen.randomPassword(len, chars);
        String hash = SHA1.hash(password);
        int count = HIBPCheck.check(hash);
        System.out.println("Len: " + len + ", Chars: " + chars + ", Count (haveibeenpwned.com): " + count + ", Password: " + password);
    }
}
