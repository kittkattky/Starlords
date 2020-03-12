package Csc340Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiPractice{
    
    static String p;
    
    
    private static HttpURLConnection connection;
    
    private static JSONObject t;
    
    private static String b = new String();
    
    
    private String mes = "Peter";
    

     private JSONObject res;

 ApiPractice() {
    this.res = t;
 }
 
 
 
// ApiPractice() {
//    this.mes = b;
// }
 

  public JSONObject getRes() {
     return this.res;
  }
    
  
  public String getMes() {
     return this.mes = "Mouse";
  }
    
    
    
    
    
    
    public static void main(String[] args) throws JSONException{
        
        
        
        //test.setText(bundle.getString("key1"));
        
        BufferedReader reader; //Reads the input stream
        String line; //reads everyline
        StringBuffer responseContent = new StringBuffer(); //used to append each line and build the response content
        try {
            //Method 1: Java.net.HttpURLConnection
            URL url = new URL ("https://api.themoviedb.org/3/movie/550?api_key=5d1004f4b1b9cf980e4bccdd0b7a9757");
            try {
                connection = (HttpURLConnection) url.openConnection();
                
                //Request setup
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000); //5000 ms = 5 secs
                connection.setReadTimeout(5000);
                
                int status = connection.getResponseCode();
                //System.out.println(status);
                
                if(status > 299){
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while((line = reader.readLine()) != null){
                        responseContent.append(line);
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while((line = reader.readLine()) != null){
                        responseContent.append(line);
                    }
                    reader.close();
                }
                
                System.out.println(responseContent.toString());
                //parse(responseContent.toString());
                p = responseContent.toString();
                t = new JSONObject(p.toString());
                System.out.println("This is t: " + t);
                System.out.println("This is p: " + p);
                
            } catch (IOException ex) {
                Logger.getLogger(ApiPractice.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ApiPractice.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            connection.disconnect();
        }
        
    }
    
    public static String parse(String responseBody) throws JSONException{
        JSONArray movies = new JSONArray(responseBody);
//        for(int i = 0; i< movies.length(); i++){
//            JSONObject mov = movies.getJSONObject(i);
//            String tmsId = mov.getString("tmsId");
//            int rootId = mov.getInt("rootId");
//            String subType = mov.getString("subType");
//            String title = mov.getString("title");
//            System.out.println(tmsId + " " + rootId + " " + subType + " " + title);
//            
//          }  
//            JSONObject r = movies.getJSONObject(0);
//            JSONArray s = r.getJSONArray("showtimes");
//            t = s.getJSONObject(0);
//            System.out.println(t);
//            t.toString();
        
        return null;
    }
    
}