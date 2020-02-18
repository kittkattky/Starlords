package ConnectToMovie;
   /**
     *
     * @Author Kylia
     * finds movies in the area at local AMC theaters
     */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class ConnectToMovie {
 /**
  *
  * @param args
  */

     public static void main(String[] args) {
        String baseUrl = "https://developers.amctheaters.com";
        String callAction = "/3/movies/";
        String apiKey = "B502B332-D4BC-4B0B-B376-3C5F3DA28308";
        String urlString = "http://developers.amctheatres.com/GettingStarted/"
                + "NewVendorSuccess?key=b502b332-d4bc-4b0b-b376-3c5f3da28308";

        URL url;
        System.out.println("Marker 1");
        try {
        System.out.println("Marker 2");
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept", "application/json" );
            con.setRequestProperty("user-key", "31dd5ae1-9562-4b1f-b718-f8b1a3a97492" );
            con.setRequestMethod("GET");
        System.out.println("Marker 3");

            int status = con.getResponseCode();

            System.out.println("Response Code: " + status);
            StringBuffer content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            con.disconnect();
            System.out.println("Output: " + content.toString());
        } catch (IOException ex) {
            Logger.getLogger(ConnectToMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

