package api.translators;

/**
 * Translator Class Opens/closes connection with Zomato api server. Main job is
 * to return data to be used in view.
 *
 * @author Diego Rodriguez Updated: 3/30/2020
 */
import api.interfaces.RestaurantApiInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import java.util.*;
import utilities.AppConfigUtil.AppConfigUtil;

public class ZomatoApi implements RestaurantApiInterface {

    private final AppConfigUtil config = new AppConfigUtil("AppConfig.properties");
    //variables needed for constructing urlString
    private final String BASEURL = this.config.getProperty("api.zomato.url");
    private final String APIKEY = this.config.getProperty("api.zomato.APIKEY");
    //Radius is in meters. Default is 15 miles = 24141 meters.
    private final int RADIUS = 24141;
    protected String version = this.config.getProperty("api.zomato.version");
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
        this.getRequestType = this.config.getProperty("api.zomato.cuisineRequestType");
        String urlString = this.BASEURL + this.version + this.getRequestType + this.config.getProperty("api.zomato.latitudeAttribute") + _lat + this.config.getProperty("api.zomato.longitudeAttribute") + _lon;

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
     * Returns an array list containing 4 maps of restaurant data (name, url,
     * address, and rating)
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
        String urlString = this.BASEURL + this.version + this.getRequestType + this.config.getProperty("api.zomato.latitudeAttribute") + _lat + this.config.getProperty("api.zomato.longitudeAttribute") + _lon
                + this.config.getProperty("api.zomato.radiusAttribute") + this.RADIUS + this.config.getProperty("api.zomato.cuisineIDAttribute") + _cuisineID;

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
            Map<String, String> urlMap = new HashMap<String, String>();
            Map<String, String> addressMap = new HashMap<String, String>();
            Map<String, String> ratingMap = new HashMap<String, String>();

            for (int i = 0; i < jsonarr.length(); i++) {
                util = (JSONObject) jsonarr.get(i);
                util2 = (JSONObject) util.get("restaurant");

                nameMap.put(i, (String) util2.get("name"));
                urlMap.put((String) util2.get("name"), (String) util2.get("url"));
                addressMap.put((String) util2.get("name"), (String) ((JSONObject) util2.get("location")).get("address"));
                //This if else statement handles a special situation. If the rating is 0, then util2 retruns an Integer.
                //This is a problem because it can't be type casted to a string, so I have to manually do it.
                if (((JSONObject) util2.get("user_rating")).get("aggregate_rating") instanceof Integer) {
                    ratingMap.put((String) util2.get("name"), "0");
                } else {
                    ratingMap.put((String) util2.get("name"), (String) ((JSONObject) util2.get("user_rating")).get("aggregate_rating"));
                }

            }

            //add maps to array list
            mapsOfRestaurantInfo.add(nameMap);
            mapsOfRestaurantInfo.add(urlMap);
            mapsOfRestaurantInfo.add(addressMap);
            mapsOfRestaurantInfo.add(ratingMap);

            return mapsOfRestaurantInfo;
        } catch (Exception ex) {
            //if an exception is caught, return null
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return content;
    }

}
