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
    protected final String baseURL = "https://www.googleapis.com/geolocation/v1/geolocate";
    protected final String userKey = "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU";
    protected final String requestMethod = "POST";
    protected final String attribute = "location";
    
    public GeoLocationAPI () {
        this.controller = new APIController (new APIModel (), this.baseURL, new String [] [] {{"key", this.userKey}});
    }
    
    @Override
    public void postRequest () {
        this.controller.submitAPIRequest(this.requestMethod, this.attribute);
        
        try {
            LinkedHashMap <String, Object> map = (LinkedHashMap <String, Object>) this.controller.toMap();
            String lat = map.get ("lat").toString();
            String lng = map.get ("lng").toString();
            
            this.setLatitude(Double.parseDouble(lat));
            this.setLongitude(Double.parseDouble(lng));
            
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
