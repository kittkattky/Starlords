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
public class GeoLocationAPI implements LocationAPIInterface {
    protected APIController controller;
    protected double latitude, longitude;
    protected final String BASE_URL = "https://www.googleapis.com/geolocation/v1/geolocate";
    protected final String USER_KEY = "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU";
    protected final String REQUEST_METHOD = "POST";
    protected final String ATTRIBUTE = "location";
    protected final String LONG_ATTR = "lng";
    protected final String LAT_ATTR = "lat";
    protected final String AUTH_KEY_ATTR = "key";
    protected final String [] [] API_PARAMS = new String [] [] {{this.AUTH_KEY_ATTR, this.USER_KEY}};
    
    @Override
    public void submitRequest () {
        this.controller.submitAPIRequest(this.REQUEST_METHOD, this.ATTRIBUTE);
        
        try {
            LinkedHashMap <String, Object> map = (LinkedHashMap <String, Object>) this.controller.toMap();
            
            //parse  latitude and longitude coordinates.
            double lat = Double.parseDouble(map.get (this.LAT_ATTR).toString());
            double lng = Double.parseDouble(map.get (this.LONG_ATTR).toString());
            
            this.setLatitude(lat);
            this.setLongitude(lng);
            
        } catch (JSONException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public double getLongitude() {
        return this.longitude;
    }
    
    public void setLatitude (double _lat) {
        this.latitude = _lat;
    }

    public void setLongitude (double _long) {
        this.longitude = _long;
    }
    
}
