package LocationAPI;

/**
 * GeoCode public class for retrieving lat-lng properties by user-provided parameters.
 * Authors: Preston Williamson
 * Last Updated Date: 10-MAR-2020
 */
public class GeoCode extends LocationAPI {
    protected final String URL = "https://maps.googleapis.com/maps/api/geocode/json";
    protected final String KEY = "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU";
    protected final String METHOD = "GET";
    protected final String ATTR = "results;geometry;location";
    protected final String LNG_ATTR = "lng";
    protected final String LT_ATTR = "lat";
    protected final String KEY_ATTR = "key";
    protected LocationAPIInterface api;

    /**
     * GeoCode constructor.
     */
    public GeoCode () {
        //set connection properties.
        this.api = new LocationAPIAdapter ();
        this.api.setURL(this.URL);
        this.api.setUserKey(this.KEY);
        this.api.setUserKeyAttributeName(this.KEY_ATTR);
        this.api.setRequestMethod(this.METHOD);
        this.api.setLocationAttributeName(this.ATTR);
        this.api.setLongitudeAttributeName(this.LNG_ATTR);
        this.api.setLatitudeAttributeName(this.LT_ATTR);
        
        //set user key.
        this.api.setAPIConfigParameter(this.KEY_ATTR, this.KEY);
    }

    /**
     * submitRequest: method dedicated to submitting the API request and store values from the result string.
     */
    @Override
    public void submitRequest () {
        this.api.submitRequest();
    }

    //================= GETTERS ===============
    /**
     * getURL: method to retrieve the URL string.
     * @return String
     */
    @Override
    public String getURL () {
        return this.api.getURL();
    }

    /**
     * getLatitude: method to retrieve the latitude.
     * @return double
     */
    @Override
    public double getLatitude () {
        return this.api.getLatitude();
    }

    /**
     * getLongitude: method to retrieve the longitude.
     * @return double
     */
    @Override
    public double getLongitude () {
        return this.api.getLongitude();
    }

    /**
     * getAPIConfigParameters: method to retrieve the String representation of the API parameters.
     * @return String
     */
    @Override
    public String getAPIConfigParameters () {
        return this.api.getAPIConfigParameters();
    }

    //================= SETTERS ===============
    /**
     * setAPIConfigParameter: method to set the tag name storing the user key property.
     * @param _key: value storing the key
     * @param _val: value the key will store.
     */
    @Override
    public void setAPIConfigParameter (String _key, String _val) {
        this.api.setAPIConfigParameter(_key, _val);
    }
}
