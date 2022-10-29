import checks.Entropy;
import checks.HIBPCheck;
import passgen.CharGroup;
import passgen.PasswordGen;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length == 0) {
            System.out.println("Running demo:");
            demo(2, CharGroup.all);
            demo(4, CharGroup.digit);
            demo(4, CharGroup.lcase);
            demo(8, CharGroup.mixed);
            demo(10, CharGroup.urlsafe);
            demo(20, CharGroup.all);
            demo(10, CharGroup.hex);
            demo(40, CharGroup.binary);
            Random r = new Random();
            CharGroup[] charGroup = CharGroup.values();
            demo(r.nextInt(100), charGroup[r.nextInt(charGroup.length)]);
        } else if (args.length % 2 == 0) {
            int[] lengths = new int[args.length / 2];
            CharGroup[] charGroup = new CharGroup[args.length / 2];
            for (int index = 0; index < args.length / 2; index += 1) {
                int len = Integer.parseInt(args[2 * index]);
                CharGroup chargroup = CharGroup.valueOf(args[2 * index + 1]);
                demo(len, chargroup);
                lengths[index] = len;
                charGroup[index] = chargroup;
            }
            if (lengths.length > 1) combinedDemo(lengths, charGroup, 10);
        }
    }

    public static void demo(int len, CharGroup charGroup) throws IOException, NoSuchAlgorithmException {
        String password = PasswordGen.randomPassword(len, charGroup);
        int count = HIBPCheck.check(password);
        int entropy = Entropy.calc(len, charGroup);
        System.out.println("Len: " + len + ", Chars: " + charGroup + ", Entropy: " + entropy + " bits, Count (haveibeenpwned.com): " + count + ", Password: " + password);
    }

    public static void combinedDemo(int[] lens, CharGroup[] chargroups, int iterations) throws IOException, NoSuchAlgorithmException {
        for (int iteration = 0; iteration < iterations; iteration++) {
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < lens.length; index += 1) {
                sb.append(PasswordGen.randomPassword(lens[index], chargroups[index]));
            }
            String password = sb.toString();
            int count = HIBPCheck.check(password);
            System.out.println("Len: " + password.length() + ", Count (haveibeenpwned.com): " + count + ", Password: " + password);
        }
    }
}
