package RestaurantV3;

/**
 * Translator Class Opens/closes connection with Zomato api server. Main job is
 * to return string of data.
 *
 * @author Diego Rodriguez Updated: 2/27/2020
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class ZomatoApi implements RestaurantApiInterface {

    //variables needed for constructing urlString
    private final String BASEURL = "https://developers.zomato.com/api";
    private final String APIKEY = "0a71dc953812d0958a14168a49b5acfd";
    protected String version = "/v2.1/";
    private String getRequestType;

    /**
     * Returns an unparsed string of cuisine data from the Zomato API server.
     *
     * @param _lat
     * @param _lon
     * @return
     */
    @Override
    public String loadCusineListByLocation(double _lat, double _lon) {
        //construct urlString
        this.getRequestType = "cuisines?";
        String urlString = this.BASEURL + this.version + this.getRequestType + "lat=" + _lat + "&lon=" + _lon;

        //try getting data from api server using try/catch block
        try {

            //create instance of url object and type cast it to httpConection type.
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            //configure HttpURLConnection object
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("user-key", this.APIKEY);
            httpConnection.setRequestMethod("GET");

            //check response code
            int responseCode = httpConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            //wrap InputStream in BufferedReader
            //read data from the input stream and store it in a string.
            BufferedReader readData;
            String inputLine;
            StringBuffer content = new StringBuffer();
            readData = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            while ((inputLine = readData.readLine()) != null) {
                content.append(inputLine);
            }

            //close connection
            readData.close();
            httpConnection.disconnect();

            //create JSONObject with the information from api server
            JSONObject obj = new JSONObject(content.toString());

            //obj.get() returns an object, so I cast it to JSONArray to make it easier to parse through.
            JSONArray jsonarr = (JSONArray) obj.get("cuisines");
            //show contents of array for demo purposes.
            System.out.println(jsonarr.toString());

            //I haven't decided how I want to parse the info yet, so here's a demo of how to return the first element in the JSONArray.
            JSONObject obj2 = (JSONObject) jsonarr.get(0);

            //return first element of JSONArray
            String cuisineList = obj2.toString();
            return cuisineList;

        } catch (Exception ex) {
            //if an exception is caught, return null
            Logger.getLogger(restaurant.ZomatoAPI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Returns an unparsed string of restaurant data from the Zomato API server.
     * @param _cuisineID
     * @param _lat
     * @param _lon
     * @return 
     */
    
    @Override
    public String loadRestaurantListByID(int _cuisineID, double _lat, double _lon) {
        //construct urlString
        this.getRequestType = "search?";
        String urlString = this.BASEURL + this.version + this.getRequestType + "lat=" + _lat + "&lon=" + _lon + "&cusines=" + _cuisineID;

        //try getting data from api server using try/catch block
        try {

            //create instance of url object and type cast it to httpConection type.
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            //configure HttpURLConnection object
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("user-key", this.APIKEY);
            httpConnection.setRequestMethod("GET");

            //check response code
            int responseCode = httpConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader readData;
            String inputLine;
            StringBuffer content = new StringBuffer();

            //wrap InputStream in BufferedReader
            //read data from the input stream and store it in a string.
            readData = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            while ((inputLine = readData.readLine()) != null) {
                content.append(inputLine);
            }

            //close connection
            readData.close();
            httpConnection.disconnect();

            //create JSONObject with the information from api server
            JSONObject obj = new JSONObject(content.toString());

            //obj.get() returns an object, so I cast it to JSONArray to make it easier to parse through.
            JSONArray jsonarr = (JSONArray) obj.get("restaurants");
            //show contents of array for demo purposes.
            System.out.println(jsonarr.toString());

            //I haven't decided how I want to parse the info yet, so here's a demo of how to return the first element in the JSONArray.
            JSONObject obj2 = (JSONObject) jsonarr.get(0);

            //return first element of JSONArray
            String restaurantList = obj2.toString();
            return restaurantList;

        } catch (Exception ex) {
            //if an exception is caught, return null
            Logger.getLogger(restaurant.ZomatoAPI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
