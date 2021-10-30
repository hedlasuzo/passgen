package checks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HIBPCheck {
    static final String API_URL = "https://api.pwnedpasswords.com/range/";

    /**
     * Check the password using the Pwned Passwords API from Have I Been Pwned
     * - https://haveibeenpwned.com/API/v3#PwnedPasswords
     */
    public static int check(String hash) throws IOException {
        String url = API_URL + hash.substring(0, 5);

        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");
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
