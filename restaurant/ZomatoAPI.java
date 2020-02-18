package restaurant;

/**
 * This class connects to the Zomato api server. Route all calls through the
 * Restaurant model.
 *
 * @author Diego Rodriguez 
 * Updated: 18-FEB-2020
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class ZomatoAPI {

    //create new restaurant object
    protected Restaurant restaurantModel = new Restaurant();
 
    //variables used in constructing url string
    private final String baseUrl = "https://developers.zomato.com/api";
    protected String version = "/v2.1";
    protected String getRequestType = this.restaurantModel.getGetRequestType();
    private String lat = "lat=" + this.restaurantModel.getLatitude();
    private String lon = "lon=" + this.restaurantModel.getLongitude();
    private final String apiKey = "0a71dc953812d0958a14168a49b5acfd";
    private int cuisineID;

    //variables/objects used in connecting to server and retrieving data
    private String urlString;
    protected URL url;
    private HttpURLConnection httpConnection;
    private BufferedReader readData;
    private String inputLine;
    private StringBuffer content = new StringBuffer();
    protected JSONObject obj;
    protected int responseCode;

    
    /**
     * constructs the url string based on the type of get request
     * @return 
     */
    public String constructURLString() {
        switch (this.restaurantModel.getGetRequestType().toLowerCase()) {
            case "cuisines":
                this.urlString = this.baseUrl + this.version + this.getRequestType + "?" + this.lat + "&" + this.lon;
                break;

            case "search":
                this.urlString = this.baseUrl + this.version + this.getRequestType + "?" + this.lat + "&" + this.lon + "&" + this.cuisineID;
                break;

            default:
                this.urlString = this.baseUrl + this.version + this.getRequestType + "?" + this.lat + "&" + this.lon;
                break;
        }

        return urlString;
    }
    /**
     * formats json string incase there are any unecessary white spaces or characters to being with. 
     * @Author: Preston
     */
    public void formatJSONString() {
        //continue chopping off leading and trailing characters until the expected first char is obtained.
        while (this.inputLine.toString().charAt(0) != '{') {
            this.inputLine = this.inputLine.toString().substring(1, this.inputLine.length() - 1);
        }
    }

    /**
     * This method returns a json object for the restaurant model to use. 
     *
     * @return
     */
    public JSONObject retrieveJSONData() {

        try {

            //this.url = new URL(constructURLString());
            this.url = new URL(this.urlString);
            this.httpConnection = (HttpURLConnection) this.url.openConnection();

            //configure HttpURLConnection object
            this.httpConnection.setRequestProperty("Accept", "application/json");
            this.httpConnection.setRequestProperty("user-key", this.apiKey);
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

            //format JSON string;
            inputLine = content.toString();
            formatJSONString();
            obj = new JSONObject(inputLine);

        } catch (Exception ex) {
            Logger.getLogger(ZomatoAPI.class.getName()).log(Level.SEVERE, null, ex);

        }

        return this.obj;
    }
}
