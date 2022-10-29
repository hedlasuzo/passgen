package checks;

import utils.SHA1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class HIBPCheck {
    static final String API_URL = "https://api.pwnedpasswords.com/range/";

    /**
     * <p>Check the password using the Pwned Passwords API from Have I Been Pwned
     * (<a href="https://haveibeenpwned.com/API/v3#PwnedPasswords">https://haveibeenpwned.com/API/v3#PwnedPasswords</a>)
     * </p><p>
     * Uses https to protect against the request being intercepted, and hash based
     * k-Anonymity so even haveibeenpwned.com cannot be sure which password being checked(
     * <a href="https://www.troyhunt.com/ive-just-launched-pwned-passwords-version-2/#cloudflareprivacyandkanonymity">
     * https://www.troyhunt.com/ive-just-launched-pwned-passwords-version-2/#cloudflareprivacyandkanonymity</a>
     * </p>
     */
    public static int check(String password) throws IOException, NoSuchAlgorithmException {
        String hash = SHA1.hash(password);
        String url = API_URL + hash.substring(0, 5);

        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("Add-Padding", "true");
        con.connect();
        if (con.getResponseCode() == 200) {
            String s = new String(con.getInputStream().readAllBytes()).toLowerCase();
            String[] lines = s.split("\r\n");
            for (String line : lines) {
                if (line.substring(0, 35).equals(hash.substring(5))) {
                    String count = line.substring(36);
                    return Integer.parseInt(count);
                }
            }
        } else {
            throw new IOException("Unexpected http response: " + con.getResponseCode() + " " + con.getResponseMessage());
        }
        return 0;
    }

}
