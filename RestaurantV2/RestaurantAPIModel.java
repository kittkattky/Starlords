package RestaurantV2;

/**
 * RestaurantAPIModel class that extends APIModel Formats JSON data from Zomato
 * api and returns it to Restaurant API Controller to be used in the view file.
 * @author Diego Rodriguez
 * Updated: 2/25/2020
 */
import ControllerModel.APIModel;
import ControllerModel.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import org.json.*;

/**
 *
 * @author Diego Rodriguez
 */
public class RestaurantAPIModel extends APIModel {

    protected String restaurantList;
    protected String cuisineList;
    protected JSONObject json;

    public RestaurantAPIModel() {
        super();
    }
    
    //returns the linked hashmaps with attributes as (key, value) pairs
    public LinkedHashMap getAttributes() {
        return this.config;
    }
    
    //formats JSON string for parser incase there are any characters before the starting curly bracket '{'
    public void formatJSONString(String _inputLine) {
        //continue chopping off leading and trailing characters until the expected first char is obtained.
        while (_inputLine.charAt(0) != '{') {
            _inputLine = _inputLine.substring(1, _inputLine.length() - 1);
        }
    }

    /**
     * This method prints the list of cuisines in the area based on location.
     * @param _dataFromApi
     * @throws JSONEException
     */
    public void printCuisineList(String _dataFromApi) throws JSONException {
        formatJSONString(_dataFromApi);
        this.json = new JSONObject(_dataFromApi);
        System.out.println(this.json.get("cuisines"));
    }

    /**
     * This method prints the list of restaurants in the area based on location, and/or cuisine code.
     * @param _dataFromApi
     * @throws JSONException
     */
    public void printRestaurantList(String _dataFromApi) throws JSONException {
        formatJSONString(_dataFromApi);
        this.json = new JSONObject(_dataFromApi);
        System.out.println(this.json.get("restaurants"));
    }

}
