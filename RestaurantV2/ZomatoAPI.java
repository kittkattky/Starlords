package RestaurantV2;

/**
 * Zomato API class that opens/closes connection with Zomato api server. Main
 * job is to return string of unparsed JSON data.
 * @author Diego Rodriguez
 * Updated: 2/25/2020
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import ControllerModel.*;

public class ZomatoAPI {

    protected RestaurantAPIModel model;

    /**
     * Constructor
     *
     * @param _model
     */
    public ZomatoAPI(RestaurantAPIModel _model) {
        this.model = _model;
    }

    //variables needed for constructing url string
    private final String BASEURL = "https://developers.zomato.com/api";
    protected String version = "/v2.1/";
    private String parameters = "";
    private final String APIKEY = "0a71dc953812d0958a14168a49b5acfd";
    private String urlString;

    //variables and objects needed for gathering data from api
    protected URL url;
    private HttpURLConnection httpConnection;
    private BufferedReader readData;
    private String inputLine;
    private StringBuffer content = new StringBuffer();
    protected JSONObject obj;
    protected int responseCode;

    /**
     * This method constructs the url string based on (key, values) from the
     * Linked HashTable "config". Linked HashTable config is called from
     * RestaurantAPIModel. Key values are set from driver.
     *
     * @return
     */
    public String constructURLString() {
        for (String key : this.model.config.keySet()) {
            this.parameters = this.parameters.concat(this.model.config.get(key));
        }
        this.urlString = this.BASEURL + this.version + this.parameters;
        return this.urlString;
    }

    /**
     * This method returns a string of unparsed json data from the Zomato api.
     *
     * @return
     */
    public String retrieveJSONData() {

        try {

            //this.url = new URL(constructURLString());
            this.url = new URL(constructURLString());
            this.httpConnection = (HttpURLConnection) this.url.openConnection();

            //configure HttpURLConnection object
            this.httpConnection.setRequestProperty("Accept", "application/json");
            this.httpConnection.setRequestProperty("user-key", this.APIKEY);
            this.httpConnection.setRequestMethod("GET");

            //check response code
            this.responseCode = this.httpConnection.getResponseCode();
            System.out.println("Response Code: " + this.responseCode);

            //wrap InputStream in BufferedReader
            //read data from the input stream and store it in a string.
            this.readData = new BufferedReader(new InputStreamReader(this.httpConnection.getInputStream()));
            while ((this.inputLine = readData.readLine()) != null) {
                content.append(inputLine);
            }

            //close connection
            this.readData.close();
            this.httpConnection.disconnect();

        } catch (Exception ex) {
            Logger.getLogger(restaurant.ZomatoAPI.class.getName()).log(Level.SEVERE, null, ex);

        }

        return this.content.toString();
    }
}
