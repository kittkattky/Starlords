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
import java.util.*;

public class ZomatoApi implements RestaurantApiInterface {

    //variables needed for constructing urlString
    private final String BASEURL = "https://developers.zomato.com/api";
    private final String APIKEY = "0a71dc953812d0958a14168a49b5acfd";
    //Radius is in meters. Default is 15 miles = 24141 meters.
    private final int RADIUS = 24141;
    protected String version = "/v2.1/";
    private String getRequestType;

    /**
     * Returns an array list that contains 2 maps. Cuisines and IDs
     *
     * @param _lat
     * @param _lon
     * @return
     */
    @Override
    public ArrayList<Map> loadCuisineListByLocation(double _lat, double _lon) {

        //construct urlString
        this.getRequestType = "cuisines?";
        String urlString = this.BASEURL + this.version + this.getRequestType + "lat=" + _lat + "&lon=" + _lon;
        //call helper method
        StringBuffer content = connectToAPI(urlString);

        ArrayList<Map> mapsOfCuisinesAndIDs = new ArrayList<Map>();

        try {
            //create JSONObject with the information from api server
            JSONObject obj = new JSONObject(content.toString());

            //obj.get() returns an object, so I cast it to JSONArray to make it easier to parse through.
            JSONArray jsonarr = (JSONArray) obj.get("cuisines");

            //I am going to store the data I want within maps.
            //Cuisine map will store cuisine strings in an ascending order starting with 0.
            //idMap will store cuisine IDs(ints) based on the acutal string name of its corresponding cuisine.
            Map<Integer, String> cuisineMap = new HashMap<Integer, String>();
            Map<String, Integer> idMap = new HashMap<String, Integer>();

            //JSONObjects needed for parsing data.
            JSONObject util;
            JSONObject util2;

            //loops through all entries within jsonarr and parses/stores the data from each index within the maps.
            for (int i = 0; i < jsonarr.length(); i++) {
                util = (JSONObject) jsonarr.get(i);
                util2 = (JSONObject) util.get("cuisine");
                cuisineMap.put(i, util2.getString("cuisine_name"));
                idMap.put(util2.getString("cuisine_name"), util2.getInt("cuisine_id"));
            }

            //Store the 2 maps within an array list in order to return both maps.
            mapsOfCuisinesAndIDs.add(cuisineMap);
            mapsOfCuisinesAndIDs.add(idMap);

        } catch (JSONException ex) {
            Logger.getLogger(ZomatoApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapsOfCuisinesAndIDs;
    }

    /**
     * Returns an array list containing 4 maps of restaurant data (name, url, address, and rating)
     *
     * @param _cuisineID
     * @param _lat
     * @param _lon
     * @return
     */
    @Override
    public ArrayList<Map> loadRestaurantListByID(int _cuisineID, double _lat, double _lon) {
        //construct urlString
        this.getRequestType = "search?";
        String urlString = this.BASEURL + this.version + this.getRequestType + "lat=" + _lat + "&lon=" + _lon + "&radius=" + this.RADIUS + "&cusines=" + _cuisineID;

        //call helper method
        StringBuffer content = connectToAPI(urlString);

        ArrayList<Map> mapsOfRestaurantInfo = new ArrayList<Map>();

        try {
            //create JSONObject with the information from api server
            JSONObject obj = new JSONObject(content.toString());

            //obj.get() returns an object, so I cast it to JSONArray to make it easier to parse through.
            JSONArray jsonarr = (JSONArray) obj.get("restaurants");

            JSONObject util;
            JSONObject util2;

            Map<Integer, String> nameMap = new HashMap<Integer, String>();
            Map<Integer, String> urlMap = new HashMap<Integer, String>();
            Map<Integer, String> addressMap = new HashMap<Integer, String>();
            Map<Integer, String> ratingMap = new HashMap<Integer, String>();

            for (int i = 0; i < jsonarr.length(); i++) {
                util = (JSONObject) jsonarr.get(i);
                util2 = (JSONObject) util.get("restaurant");

                nameMap.put(i, (String) util2.get("name"));
                urlMap.put(i, (String) util2.get("url"));
                addressMap.put(i, (String) ((JSONObject) util2.get("location")).get("address"));
                ratingMap.put(i, (String) ((JSONObject) util2.get("user_rating")).get("aggregate_rating"));
            }

            //add maps to array list
            mapsOfRestaurantInfo.add(nameMap);
            mapsOfRestaurantInfo.add(urlMap);
            mapsOfRestaurantInfo.add(addressMap);
            mapsOfRestaurantInfo.add(ratingMap);

            return mapsOfRestaurantInfo;
        } catch (Exception ex) {
            //if an exception is caught, return null
            Logger.getLogger(restaurant.ZomatoAPI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public StringBuffer connectToAPI(String urlString) {

        StringBuffer content = new StringBuffer();

        //try getting data from api server using try/catch block
        try {

            //create instance of url object and type cast it to httpConection type.
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            //configure HttpURLConnection object
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("user-key", this.APIKEY);
            httpConnection.setRequestMethod("GET");

            //check response code, if needed
            /*
            int responseCode = httpConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            */

            //wrap InputStream in BufferedReader
            //read data from the input stream and store it in a string.
            BufferedReader readData;
            String inputLine;
            readData = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            while ((inputLine = readData.readLine()) != null) {
                content.append(inputLine);
            }

            //close connection
            readData.close();
            httpConnection.disconnect();
        } catch (Exception ex) {
            //if an exception is caught, return null
            Logger.getLogger(restaurant.ZomatoAPI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return content;
    }

}
