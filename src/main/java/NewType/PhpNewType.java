package NewType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class PhpNewType {
    private static HttpURLConnection con;


    public String newType(String nazwa, String id_autora, String c_k_p, String codePod, String minNas, String maxNas, String codeNas, String minTem, String maxTem, String minWil, String maxWil, String www) throws MalformedURLException,
            ProtocolException, IOException {

        StringBuilder link = new StringBuilder("http://serwer1727017.home.pl/2ti/floda/add/add_genre.php?name="+nazwa+"&autor="+id_autora+"&podlewanie="+ c_k_p +"&naslonecznieniemin="+ minNas +"&naslonecznieniemax="+ maxNas +"&tempmin="+ minTem +"&tempmax="+ maxTem +"&wilgmin="+ minWil +"&wilgmax="+ maxWil +"&www="+www);

        if(!codePod.equals("")){
            link.append("&podlewanieZ=").append(codePod);
        }

        if(!codeNas.equals("")){
            link.append("&naslonecznienieZ=").append(codeNas);
        }
        System.out.println(link.toString());

        URL url = new URL(link.toString());
        URLConnection connection = url.openConnection();
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine=in.readLine();

        in.close();

        if(inputLine.equals("0"))
        {
            return "Podana nazwa jest już zajęta";
        }
        else{
            return "Dodano nowy gatunek";
        }

    }

}
