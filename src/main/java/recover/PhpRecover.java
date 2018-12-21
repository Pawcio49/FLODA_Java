package recover;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PhpRecover {
    private static HttpURLConnection con;

    public int mail(String mail) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/mailCode.php";
        String urlParameters = "mail="+mail;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int line;

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

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                line = Integer.parseInt(in.readLine());

            }

            return line;

        } finally {

            con.disconnect();
        }
    }

    public int verify(String mail, String code) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/verifyCode.php";
        String urlParameters = "mail=" + mail + "&id=" + code;
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

                int line;

                line = Integer.parseInt(in.readLine());

                return line;
            }
        } finally {

                con.disconnect();
        }

    }

    public String change(String mail, String newPass, String verPass) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/changePassword.php";
        String urlParameters = "mail=" + mail + "&newPass=" + newPass + "&verPass="+verPass ;
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

