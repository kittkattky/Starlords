package LocationAPI;

import API.*;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 * LocationAPI public class for implementing LocationAPIInterface
 * Authors: Preston Williamson
 * Last Updated Date: 10-MAR-2020
 */
public class LocationAPI implements LocationAPIInterface {
    protected APIController control;
    protected double latitude, longitude;
    protected String BASE_URL;
    protected String USER_KEY;
    protected String REQUEST_METHOD;
    protected String ATTRIBUTE;
    protected String LONG_ATTR;
    protected String LAT_ATTR;
    protected String AUTH_KEY_ATTR;
    protected LinkedHashMap <String, String> config = new LinkedHashMap <> ();

    /**
     * submitRequest: method dedicated to submitting the API request and store values from the result string.
     */
    @Override
    public void submitRequest() {

        //submit request.
        this.control = new APIController (this.getURL(), this.getUserKey(), this.getAPIConfigParamObject());
        this.control.submitAPIRequest(this.getRequestMethod(), this.getLocationAttributeName());

        try {
            //get the map representation of the results and extract latitude and longitude.
            LinkedHashMap <String, Object> map = (LinkedHashMap <String, Object>) this.control.toMap();
            String lat = map.get (this.getLatitudeAttributeName()).toString();
            String lng = map.get (this.getLongitudeAttributeName()).toString();

            this.setLatitude(Double.parseDouble(lat));
            this.setLongitude(Double.parseDouble(lng));

        } catch (JSONException ex) {
            Logger.getLogger(LocationAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //================= GETTERS ===============
    /**
     * getURL: method to retrieve the URL string.
     * @return String
     */
    @Override
    public String getURL() {
        return this.BASE_URL;
    }

    /**
     * getUserKey: method to retrieve the user key string.
     * @return String
     */
    @Override
    public String getUserKey() {
        return this.USER_KEY;
    }

    /**
     * getRequestMethod: method to retrieve the request method.
     * @return String
     */
    @Override
    public String getRequestMethod() {
        return this.REQUEST_METHOD;
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
     * getAPIConfigParamObject: method to retrieve the config map object.
     * @return LinkedHashMap <String, String>
     */
    @Override
    public LinkedHashMap <String, String> getAPIConfigParamObject() {
        return this.config;
    }

    /**
     * getAPIConfigParameters: method to retrieve the String representation of the API parameters.
     * @return String
     */
    @Override
    public String getAPIConfigParameters () {
        return this.control.getAPIConfigParameters();
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

    //================= SETTERS ===============
    /**
     * setURL: method to set the URL.
     * @param _url
     */
    @Override
    public void setURL(String _url) {
        this.BASE_URL = _url;
    }

    /**
     * setUserKey: method to set the user key.
     * @param _userKey
     */
    @Override
    public void setUserKey(String _userKey) {
        this.USER_KEY = _userKey;
    }

    /**
     * setRequestMethod: method to set the request method.
     * @param _reqMethod
     */
    @Override
    public void setRequestMethod(String _reqMethod) {
        this.REQUEST_METHOD = _reqMethod;
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
        this.config.put(_key, _val);
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
