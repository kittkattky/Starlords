package api.translators;

/**
 * Translator class, takes information
 * from the eventful API
 * 
 * @author Kahlie
 * @date 4/2/20
 */
import api.interfaces.EventsAPIInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventfulApi implements EventsAPIInterface {

    private static HttpURLConnection connection;

    //Constants
    private static final String baseUrl = "http://api.eventful.com/json/";
    private static final String apiKey = "&app_key=KH7kFfxFkzKGnkLf";
    private static final String categories = "categories/list?&";

    /**
     * Parses the json data and places the events categories into a map
     * @param _zipcode
     * @return
     * @throws JSONException
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException
     */
    @Override
    public ArrayList<Map> loadCategories() throws JSONException, MalformedURLException, ProtocolException, IOException {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        URL url = new URL(this.baseUrl + this.categories + this.apiKey);

        this.connection = (HttpURLConnection) url.openConnection();
        this.connection.setRequestMethod("GET");
        this.connection.setConnectTimeout(5000);
        this.connection.setReadTimeout(5000);

        int status = this.connection.getResponseCode();

        ArrayList<Map> mapsOfCategories = new ArrayList<Map>();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        } else {
            reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        }

        JSONObject obj = new JSONObject(responseContent.toString());

        JSONArray arr = (JSONArray) obj.get("category");

        Map<Integer, String> categoriesMap = new LinkedHashMap<>();

        JSONObject categories;

        for (int i = 0; i < arr.length(); i++) {

            categories = (JSONObject) arr.get(i);
            categoriesMap.put(i, categories.getString("name").replaceAll("&amp;", "and"));
        }

        mapsOfCategories.add(categoriesMap);

        connection.disconnect();

        return mapsOfCategories;
    }

    /**
     * Parses the json data and places the event info into maps
     * @param _category
     * @param _zipcode
     * @return
     * @throws JSONException
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException
     */
    @Override
    public ArrayList<Map> loadEventsByZipcode(String _category, int _zipcode) throws JSONException, MalformedURLException, ProtocolException, IOException {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        URL url = new URL(this.baseUrl + "events/search?keywords=" +_category + "&" + "l=" + _zipcode + "&within=25&units=miles&" + this.apiKey);

        this.connection = (HttpURLConnection) url.openConnection();
        this.connection.setRequestMethod("GET");
        this.connection.setConnectTimeout(5000);
        this.connection.setReadTimeout(5000);

        int status = this.connection.getResponseCode();

        ArrayList<Map> mapsOfEvents = new ArrayList<Map>();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        } else {
            reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        }

        JSONObject obj = new JSONObject(responseContent.toString());
        JSONObject events = obj.getJSONObject("events");

        JSONArray event = events.getJSONArray("event");

        Map<Integer, String> title = new LinkedHashMap<Integer, String>();
        Map<String, String> venueName = new HashMap<String, String>();
        Map<String, String> image = new LinkedHashMap<String, String>();
        Map<String, String> description = new LinkedHashMap<String, String>();
        Map<String, String> urlLink = new LinkedHashMap<String, String>();
        Map<String, String> venueAddress = new LinkedHashMap<String, String>();

        for (int i = 0; i < event.length(); i++) {

            JSONObject info = event.getJSONObject(i);
            title.put(i, info.getString("title"));
            venueName.put(info.getString("title"), info.getString("venue_name"));
            urlLink.put(info.getString("title"), info.getString("url"));
            venueAddress.put(info.getString("title"), info.getString("venue_address"));

            if(info.isNull("description")){
                description.put(info.getString("title"), "Description not available.");
            }else{
                description.put(info.getString("title"), info.getString("description"));
            }
            if (info.isNull("image")) {
                    image.put(info.getString("title"), "https://i.imgur.com/QeEdrPk.jpg");
            } else {
                JSONObject imagef = info.getJSONObject("image");
                JSONObject medImage = (JSONObject) imagef.get("medium");
                image.put(info.getString("title"), medImage.getString("url"));
            }
        }
        mapsOfEvents.add(title);
        mapsOfEvents.add(venueName);
        mapsOfEvents.add(image);
        mapsOfEvents.add(description);
        mapsOfEvents.add(urlLink);
        mapsOfEvents.add(venueAddress);

        this.connection.disconnect();
        return mapsOfEvents;
    }
}
