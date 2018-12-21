package NewType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PhpNewType {
    private static HttpURLConnection con;

    public String newType(String whose, String nazwa, String s_d_s, String a_w_g, String c_k_p, String s_d_t, String s_d_w, String www) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/AddNewType.php";
        String urlParameters = "whose="+whose+"&nazwa="+nazwa+"&s_d_s="+s_d_s+"&a_w_g="+a_w_g+"&c_k_p="+c_k_p+"&s_d_t="+s_d_t+"&s_d_w="+s_d_w+"&www="+www;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString();

        } finally {

            con.disconnect();
        }
    }



}
