package LocationAPI;

import java.util.LinkedHashMap;

/**
 * LocationAPIAdapter class that will implement the location interface.
 * Authors: Preston Williamson
 * Last Updated Date: 10-MAR-2020
 */
public class LocationAPIAdapter implements LocationAPIInterface {
    protected final LocationAPIInterface api = new LocationAPI ();

    /**
     * submitRequest: method dedicated to submitting the API request and store values from the result string.
     */
    @Override
    public void submitRequest() {
        this.api.submitRequest();
    }

    //================= GETTERS ===============
    /**
     * getURL: method to retrieve the URL string.
     * @return String
     */
    @Override
    public String getURL() {
        return this.api.getURL();
    }

    /**
     * getUserKey: a method that handles retrieving the internal user key string.
     * @return String
     */
    @Override
    public String getUserKey() {
        return this.api.getUserKey();
    }

    /**
     * getRequestMethod: a method dedicated to retrieving the API request method.
     * @return String
     */
    @Override
    public String getRequestMethod() {
        return this.api.getRequestMethod();
    }

    /**
     * getLocationAttributeName: method to retrieve the tag name that stores the location.
     * @return String
     */
    @Override
    public String getLocationAttributeName() {
        return this.api.getLocationAttributeName();
    }

    /**
     * getLatitudeAttributeName: method to retrieve the tag name that stores the latitude.
     * @return String
     */
    @Override
    public String getLatitudeAttributeName() {
        return this.api.getLatitudeAttributeName();
    }

    /**
     * getLongitudeAttributeName: method to retrieve the tag name that stores the longitude.
     * @return String
     */
    @Override
    public String getLongitudeAttributeName() {
        return this.api.getLongitudeAttributeName();
    }

    /**
     * getUserKeyAttributeName: method to retrieve the tag name that stores the user key.
     * @return String
     */
    @Override
    public String getUserKeyAttributeName() {
        return this.api.getUserKeyAttributeName();
    }

    /**
     * getAPIConfigParamObject: method to retrieve the config map object.
     * @return LinkedHashMap <String, String>
     */
    @Override
    public LinkedHashMap <String, String> getAPIConfigParamObject() {
        return this.api.getAPIConfigParamObject();
    }

    /**
     * getAPIConfigParameters: method to retrieve the String representation of the API parameters.
     * @return String
     */
    @Override
    public String getAPIConfigParameters () {
        return this.api.getAPIConfigParameters();
    }

    /**
     * getLatitude: method to retrieve the latitude.
     * @return double
     */
    @Override
    public double getLatitude() {
        return this.api.getLatitude();
    }

    /**
     * getLongitude: method to retrieve the longitude.
     * @return double
     */
    @Override
    public double getLongitude() {
        return this.api.getLongitude();
    }

    //================= SETTERS ===============
    /**
     * setURL: method to set the URL.
     * @param _url
     */
    @Override
    public void setURL(String _url) {
        this.api.setURL(_url);
    }

   /**
     * setUserKey: method to set the user key.
     * @param _userKey
     */
    @Override
    public void setUserKey(String _userKey) {
        this.api.setUserKey(_userKey);
    }

    /**
     * setRequestMethod: method to set the request method.
     * @param _reqMethod
     */
    @Override
    public void setRequestMethod(String _reqMethod) {
        this.api.setRequestMethod(_reqMethod);
    }

    /**
     * setLocationAttributeName: method to set the tag name storing the location property.
     * @param _attr
     */
    @Override
    public void setLocationAttributeName(String _attr) {
        this.api.setLocationAttributeName(_attr);
    }

    /**
     * setLatitudeAttributeName: method to set the tag name storing the latitude property.
     * @param _attr
     */
    @Override
    public void setLatitudeAttributeName(String _attr) {
        this.api.setLatitudeAttributeName(_attr);
    }

    /**
     * setLongitudeAttributeName: method to set the tag name storing the longitude property.
     * @param _attr
     */
    @Override
    public void setLongitudeAttributeName(String _attr) {
        this.api.setLongitudeAttributeName(_attr);
    }

    /**
     * setUserKeyAttributeName: method to set the tag name storing the user key property.
     * @param _attr
     */
    @Override
    public void setUserKeyAttributeName(String _attr) {
        this.api.setUserKeyAttributeName(_attr);
    }

    /**
     * setAPIConfigParameter: method to set the tag name storing the user key property.
     * @param _key: value storing the key
     * @param _val: value the key will store.
     */
    @Override
    public void setAPIConfigParameter(String _key, String _val) {
        this.api.setAPIConfigParameter(_key, _val);
    }

    /**
     * setLatitude: method to set the latitude.
     * @param _lat: double representation of latitude value.
     */
    @Override
    public void setLatitude(double _lat) {
        this.api.setLatitude(_lat);
    }

    /**
     * setLongitude: method to set the longitude.
     * @param _lng: double representation of longitude value.
     */
    @Override
    public void setLongitude(double _lng) {
        this.api.setLongitude(_lng);
    }
}
