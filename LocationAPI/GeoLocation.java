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
public class GeoLocation extends LocationAPI {
    protected final String URL = "https://www.googleapis.com/geolocation/v1/geolocate";
    protected final String KEY = "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU";
    protected final String METHOD = "POST";
    protected final String ATTR = "location";
    protected final String LNG_ATTR = "lng";
    protected final String LT_ATTR = "lat";
    protected final String KEY_ATTR = "key";
    protected final String [] [] PARAMS = new String [] [] {{this.KEY_ATTR, this.KEY}};
    protected LocationAPIInterface api;

    public GeoLocation () {
        this.api = new LocationAPIAdapter ();
        this.api.setURL(this.URL);
        this.api.setUserKey(this.KEY);
        this.api.setUserKeyAttributeName(this.KEY_ATTR);
        this.api.setRequestMethod(this.METHOD);
        this.api.setLocationAttributeName(this.ATTR);
        this.api.setLongitudeAttributeName(this.LNG_ATTR);
        this.api.setLatitudeAttributeName(this.LT_ATTR);
        this.api.setAPIConfigParams(this.PARAMS);
    }

    @Override
    public void submitRequest () {
        this.api.submitRequest();
    }

    @Override
    public double getLatitude () {
        return this.api.getLatitude();
    }

    @Override
    public double getLongitude () {
        return this.api.getLongitude();
    }
}
