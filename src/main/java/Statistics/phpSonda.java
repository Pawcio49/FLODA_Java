package Statistics;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class phpSonda {
    private static HttpURLConnection con;

    public String modifySonda(String time, String password, int id_sondy) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/modifySonda.php";
        String urlParameters = "time=" + time + "&password=" + password + "&id_sondy=" + id_sondy;
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
                }
            }
            return content.toString();

        } finally {

            con.disconnect();
        }
    }

    public String getTime(int id_sondy) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/getTime.php";
        String urlParameters = "&id_sondy=" + id_sondy;
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

            int content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                content= Integer.parseInt(in.readLine());
            }
            return String.valueOf(content);

        } finally {

            con.disconnect();
        }
    }

    public int getWatered(int id) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "http://serwer1727017.home.pl/2ti/floda/app/getWatered.php";
        String urlParameters = "&id=" + id;
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

            String watered;
            String date;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                date= in.readLine();
                watered= in.readLine();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            //String x = String.valueOf(LocalTime.now());
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



            LocalDateTime dateTime1= LocalDateTime.parse(watered, formatter);
            LocalDateTime dateTime2= LocalDateTime.parse(sdf.format(cal.getTime()), formatter);


            int diffInDays = (int) java.time.Duration.between(dateTime1, dateTime2).toDays();

            return diffInDays;

        } finally {

            con.disconnect();
        }
    }
}