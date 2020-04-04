package location;

import LocationAPI.*;
import appconfig.*;
import java.util.LinkedHashMap;

public class Driver {
     public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException, Exception {
        //Allocate memory for LocationAPI Object.
        Location api;
        
        LinkedHashMap <String, String> param = new LinkedHashMap <> ();
        param.put("address", "27370");

        //Initialize to geocode object reference
        api = new Location (Location.GEO_CODE, param);
        api.submitRequest();
        print (api.getLatitude());
        print (api.getLongitude());
        
        //Initialize to geocode object reference
        api = new Location (Location.GEO_CODE);
        api.setAPIConfigParameter("address", "27370");
        api.submitRequest();
        print (api.getLatitude());
        print (api.getLongitude());        

        api = new Location (Location.GEO_LOCATION);
        api.submitRequest();
        print (api.getLatitude());
        print (api.getLongitude());
    }
     
     public static void print (Object o)
     {
         System.out.println (o.toString());
     }
}