package location;

import LocationAPI.*;
import API.*;
import java.util.*;
import org.json.JSONException;

public class Location {
     public static void main(String[] args) throws JSONException {
        /*final String userKey = "AIzaSyAVJFd7htTKbeo7if-p-NxCNOiVDdN7kdU";
        String lat;
        
        //Sandbox logic for working with GeoCode by ZIP code.
        String geoCodeURL = "https://maps.googleapis.com/maps/api/geocode/json";
        String [] [] geoCodeParams = new String [] [] {{"key", userKey}, {"address", "27409"}};

        APIModel geoCodeModel = new APIModel ();
        APIController geoCodeControl = new APIController (geoCodeModel, geoCodeURL, userKey, geoCodeParams);

        //get API return string.
        geoCodeControl.submitAPIRequest("GET", "results;geometry;location");
        LinkedHashMap <String, Object> map = geoCodeControl.toMap();
        for (String key : map.keySet()) {
            Object val = map.get(key);
            System.out.println (key + " = " + val);
        }*/

        LocationAPIInterface api = new GeoLocationAPIAdapter ();
        api.submitRequest();
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());
    }
}