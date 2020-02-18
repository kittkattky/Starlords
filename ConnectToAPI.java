/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connecttoapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 * @author Diego
 *gets popular cuisines and resturaunts around the specified coordinates
 */
public class ConnectToAPI {
   
     public static void main(String[] args) {
         
        //parameters needed for urlString
        String requestURL = "https://developers.zomato.com/api";
        String version = "/v2.1";
        String getRequest = "/geocode";
        final String lat = "lat=36.066984";
        final String lon = "lon=-79.800178";
        String apiKey = "0a71dc953812d0958a14168a49b5acfd";
        String urlString = requestURL + version + getRequest + "?" + lat + "&" + lon;
       
        //create url Object
        URL url;
       
        try {
           
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
           
            //configure HttpURLConnection object
            con.setRequestProperty("Accept", "application/json" );
            con.setRequestProperty("user-key", apiKey );
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            System.out.println("Response Code: " + status);
           
            //wrap InputStream in BufferedReader
            //read data from the input stream and store it in a string.
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
           
            //close connection
            in.close();
            con.disconnect();
           
            System.out.println("Output: " + content.toString());
           
            //parse data from stringBuffer object using JSONObject
            JSONObject obj = new JSONObject(content.toString());
            String popularity = obj.getString("popularity");
            System.out.println("Top Cuisines: " + popularity);
           
        } catch (Exception ex) {
            Logger.getLogger(ConnectToAPI.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
   
}
