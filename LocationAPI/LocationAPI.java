/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocationAPI;

import API.*;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author prest
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
    protected String [] [] API_PARAMS;

    @Override
    public void submitRequest() {
        this.control = new APIController (new APIModel (), this.getURL(), this.getUserKey(), this.getAPIConfigParams());
        this.control.submitAPIRequest(this.getRequestMethod(), this.getLocationAttributeName());

        try {
            LinkedHashMap <String, Object> map = (LinkedHashMap <String, Object>) this.control.toMap();
            String lat = map.get (this.getLatitudeAttributeName()).toString();
            String lng = map.get (this.getLongitudeAttributeName()).toString();

            this.setLatitude(Double.parseDouble(lat));
            this.setLongitude(Double.parseDouble(lng));

        } catch (JSONException ex) {
            Logger.getLogger(LocationAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getURL() {
        return this.BASE_URL;
    }

    @Override
    public String getUserKey() {
        return this.USER_KEY;
    }

    @Override
    public String getRequestMethod() {
        return this.REQUEST_METHOD;
    }

    @Override
    public String getLocationAttributeName() {
        return this.ATTRIBUTE;
    }

    @Override
    public String getLatitudeAttributeName() {
        return this.LAT_ATTR;
    }

    @Override
    public String getLongitudeAttributeName() {
        return this.LONG_ATTR;
    }

    @Override
    public String getUserKeyAttributeName() {
        return this.AUTH_KEY_ATTR;
    }

    @Override
    public String[][] getAPIConfigParams() {
        return this.API_PARAMS;
    }

    @Override
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public double getLongitude() {
       return this.longitude;
    }

    @Override
    public void setURL(String _url) {
        this.BASE_URL = _url;
    }

    @Override
    public void setUserKey(String _userKey) {
        this.USER_KEY = _userKey;
    }

    @Override
    public void setRequestMethod(String _reqMethod) {
        this.REQUEST_METHOD = _reqMethod;
    }

    @Override
    public void setLocationAttributeName(String _attr) {
        this.ATTRIBUTE = _attr;
    }

    @Override
    public void setLatitudeAttributeName(String _attr) {
        this.LAT_ATTR = _attr;
    }

    @Override
    public void setLongitudeAttributeName(String _attr) {
        this.LONG_ATTR = _attr;
    }

    @Override
    public void setUserKeyAttributeName(String _attr) {
        this.AUTH_KEY_ATTR = _attr;
    }

    @Override
    public void setAPIConfigParams(String[][] _params) {
        this.API_PARAMS = _params;
    }

    @Override
    public void setLatitude(double _lat) {
        this.latitude = _lat;
    }

    @Override
    public void setLongitude(double _lng) {
        this.longitude = _lng;
    }
}
