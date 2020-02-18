package restaurant;

/**
 * This class is a model class that handles data coming from the controller and
 * api class.
 *
 * @author Diego Rodriguez
 * Updated: 18-FEB-2020
 */
import org.json.*;

public class Restaurant {


    protected String getRequestType;
    protected JSONObject jsonObject;
    //lat and lon are magic numbers for now
    final private double latitude = 36.066984;
    final private double longitude = -79.800178;
    protected String cuisineList;
    protected String restaurantList;


    /**
     * Uses json parser to parse data and returns a list of cuisines.
     * @return
     * @throws JSONException
     */
    public String cuisineList() throws JSONException {
        this.cuisineList = this.jsonObject.getString("cuisines");
        return cuisineList;
    }

    /**
     * Uses json parser to parse data and returns a list of restaurants.
     * @return
     * @throws JSONException
     */
    public String resturauntList() throws JSONException {
        this.restaurantList = this.jsonObject.getString("restaurants");
        return restaurantList;
    }

    //=================  GETTERS ===============
    public String getGetRequestType() {
        return this.getRequestType;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    //=================  SETTERS ===============
    
    /**
     * sets get request type, called from controller. 
     * Two types for now
     *  1)"cuisines"
     *  2)"search"
     * @param _usersRequest 
     */
    public void setGetRequestType(String _usersRequest) {
        this.getRequestType = _usersRequest;
    }

}
