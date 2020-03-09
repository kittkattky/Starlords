package location;

import LocationAPI.*;
import java.util.Arrays;

public class Location {
     public static void main(String[] args) {
        //Allocate memory for LocationAPI Object.
        LocationAPI api;
        
        //Initialize to geocode object reference
        api = new GeoCode ();
        api.submitRequest();
        System.out.println (api.getClass().getName() + " parameters: [" + Arrays.toString(api.getAPIConfigParams()) + "]");
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());
        
        
        api = new GeoLocation ();
        api.submitRequest();
        System.out.println (api.getClass().getName());
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());        
    }
}