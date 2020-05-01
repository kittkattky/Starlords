package api.adapters;

import models.APIModel;
import controllers.APIController;
import api.interfaces.LocationAPIInterface;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import utilities.AppConfigUtil.AppConfigUtil;

/**
 * LocationAPI public class for implementing LocationAPIInterface
 * Authors: Preston Williamson
 * Last Updated Date: 10-MAR-2020
 */
public final class LocationAPIAdapter implements LocationAPIInterface {
    protected APIController control;
    protected APIModel model = new APIModel ();
    protected double latitude, longitude;
    protected String BASE_URL;
    protected String USER_KEY;
    protected String REQUEST_METHOD;
    protected String ATTRIBUTE;
    protected String LONG_ATTR;
    protected String LAT_ATTR;
    protected String AUTH_KEY_ATTR;
    protected String API_INDICATOR;
    
    private final AppConfigUtil config = new AppConfigUtil ("AppConfig.properties");

    public LocationAPIAdapter (String _property) {
        this.setAPIIndicator(_property);
        this.setURL(this.getConfigProperty("url"));
        this.setUserKey(this.getConfigProperty("userKey"));
        this.setUserKeyAttributeName(this.getConfigProperty("userKeyAttribute"));
        this.setRequestMethod(this.getConfigProperty("requestMethod"));
        this.setLocationAttributeName(this.getConfigProperty("locationAttribute"));
        this.setLongitudeAttributeName(this.getConfigProperty("longitudeAttribute"));
        this.setLatitudeAttributeName(this.getConfigProperty("latitudeAttribute"));

        //set user key.
        this.setAPIConfigParameter(this.getConfigProperty("userKeyAttribute"), this.getConfigProperty("userKey"));
    }

    /**
     * submitRequest: method dedicated to submitting the API request and store values from the result string.
     */
    @Override
    public void submitRequest() {

        //submit request.
        this.control = new APIController(this.model);
        this.control.submitAPIRequest(this.getRequestMethod(), this.getLocationAttributeName());

        try {
            //get the map representation of the results and extract latitude and longitude.
            LinkedHashMap <String, Object> map = (LinkedHashMap <String, Object>) this.control.toMap();
            String lat = map.get (this.getLatitudeAttributeName()).toString();
            String lng = map.get (this.getLongitudeAttributeName()).toString();

            double parseLat = Double.parseDouble(lat);
            double parseLong = Double.parseDouble(lng);

            this.setLatitude(parseLat);
            this.setLongitude(parseLong);

        } catch (JSONException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    //================= GETTERS ===============
    /**
     * getURL: method to retrieve the URL string.
     * @return String
     */
    @Override
    public String getURL() {
        return this.model.getURLString();
    }

    /**
     * getUserKey: method to retrieve the user key string.
     * @return String
     */
    @Override
    public String getUserKey() {
        return this.model.getUserKey();
    }

    /**
     * getRequestMethod: method to retrieve the request method.
     * @return String
     */
    @Override
    public String getRequestMethod() {
        return this.model.getRequestMethod();
    }

    /**
     * getLocationAttributeName: method to retrieve the tag name that stores the location.
     * @return String
     */
    @Override
    public String getLocationAttributeName() {
        return this.ATTRIBUTE;
    }

    /**
     * getLatitudeAttributeName: method to retrieve the tag name that stores the latitude.
     * @return String
     */
    @Override
    public String getLatitudeAttributeName() {
        return this.LAT_ATTR;
    }

    /**
     * getLongitudeAttributeName: method to retrieve the tag name that stores the longitude.
     * @return String
     */
    @Override
    public String getLongitudeAttributeName() {
        return this.LONG_ATTR;
    }

    /**
     * getUserKeyAttributeName: method to retrieve the tag name that stores the user key.
     * @return String
     */
    @Override
    public String getUserKeyAttributeName() {
        return this.AUTH_KEY_ATTR;
    }

    /**
     * getAPIConfigParameters: method to retrieve the String representation of the API parameters.
     * @return String
     */
    @Override
    public String getAPIConfigParameters () {
        return this.control.getAPIConfigParameters();
    }
    
    @Override
    public String getAPIIndicator() {
        return this.API_INDICATOR;
    }

    /**
     * getLatitude: method to retrieve the latitude.
     * @return double
     */
    @Override
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * getLongitude: method to retrieve the longitude.
     * @return double
     */
    @Override
    public double getLongitude() {
       return this.longitude;
    }
    
    @Override
    public String getConfigProperty (String _key) {
        return this.config.getProperty(this.getAPIIndicator() + _key);
    }

    //================= SETTERS ===============
    /**
     * setURL: method to set the URL.
     * @param _url
     */
    @Override
    public void setURL(String _url) {
        this.model.setURLString(_url);
    }

    /**
     * setUserKey: method to set the user key.
     * @param _userKey
     */
    @Override
    public void setUserKey(String _userKey) {
        this.model.setUserKey(_userKey);
    }

    /**
     * setRequestMethod: method to set the request method.
     * @param _reqMethod
     */
    @Override
    public void setRequestMethod(String _reqMethod) {
        this.model.setRequestMethod(_reqMethod);
    }

    /**
     * setLocationAttributeName: method to set the tag name storing the location property.
     * @param _attr
     */
    @Override
    public void setLocationAttributeName(String _attr) {
        this.ATTRIBUTE = _attr;
    }

    /**
     * setLatitudeAttributeName: method to set the tag name storing the latitude property.
     * @param _attr
     */
    @Override
    public void setLatitudeAttributeName(String _attr) {
        this.LAT_ATTR = _attr;
    }

    /**
     * setLongitudeAttributeName: method to set the tag name storing the longitude property.
     * @param _attr
     */
    @Override
    public void setLongitudeAttributeName(String _attr) {
        this.LONG_ATTR = _attr;
    }

    /**
     * setUserKeyAttributeName: method to set the tag name storing the user key property.
     * @param _attr
     */
    @Override
    public void setUserKeyAttributeName(String _attr) {
        this.AUTH_KEY_ATTR = _attr;
    }

    /**
     * setAPIConfigParameter: method to set the tag name storing the user key property.
     * @param _key: value storing the key
     * @param _val: value the key will store.
     */
    @Override
    public void setAPIConfigParameter(String _key, String _val) {
        this.model.setAPIConfigParameter(_key, _val);
    }
    
    @Override
    public void setAPIIndicator(String _indicator) {
        this.API_INDICATOR = _indicator;
    }

    /**
     * setLatitude: method to set the latitude.
     * @param _lat: double representation of latitude value.
     */
    @Override
    public void setLatitude(double _lat) {
        this.latitude = _lat;
    }

    /**
     * setLongitude: method to set the longitude.
     * @param _lng: double representation of longitude value.
     */
    @Override
    public void setLongitude(double _lng) {
        this.longitude = _lng;
    }
}
