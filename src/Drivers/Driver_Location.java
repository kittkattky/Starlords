package test;

import java.util.LinkedHashMap;
import utilities.LocationUtil.LocationUtil;

public class Driver_Location {
     public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException, Exception {
        //Allocate memory for LocationAPI Object.
        LocationUtil api;
        
        LinkedHashMap <String, String> param = new LinkedHashMap <> ();
        param.put("address", "27370");

        //Initialize to geocode object reference
        api = new LocationUtil (LocationUtil.GEO_CODE, param);
        api.submitRequest();
        print (api.getLatitude());
        print (api.getLongitude());
        
        //Initialize to geocode object reference
        api = new LocationUtil (LocationUtil.GEO_CODE);
        api.setAPIConfigParameter("address", "27370");
        api.submitRequest();
        print (api.getLatitude());
        print (api.getLongitude());        

        api = new LocationUtil (LocationUtil.GEO_LOCATION);
        api.submitRequest();
        print (api.getLatitude());
        print (api.getLongitude());
    }
     
     public static void print (Object o)
     {
         System.out.println (o.toString());
     }
}