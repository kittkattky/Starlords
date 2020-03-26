package location;

import LocationAPI.*;

public class Driver {
     public static void main(String[] args) throws NoSuchFieldException {
        //Allocate memory for LocationAPI Object.
        LocationAPIAdapter api;

        //Initialize to geocode object reference
        api = new GeoCode ();
        api.setAPIConfigParameter("address", "27370");
        api.submitRequest();
        System.out.println (api.getClass().getName() + " URL [" + api.getURL() + "/" + api.getAPIConfigParameters() + "]");
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());


        api = new GeoLocation ();
        api.submitRequest();
        System.out.println (api.getClass().getName() + " URL [" + api.getURL() + "/" + api.getAPIConfigParameters() + "]");
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());
    }
}