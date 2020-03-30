package location;

import LocationAPI.*;

public class Driver {
     public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        //Allocate memory for LocationAPI Object.
        Location api;

        //Initialize to geocode object reference
        api = new Location (Location.GEO_CODE);
        api.setAPIConfigParameter("address", "27370");
        api.submitRequest();
        System.out.println (api.getClass().getName() + " URL [" + api.getURL() + "/" + api.getAPIConfigParameters() + "]");
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());

        api = new Location (Location.GEO_LOCATION);
        api.api.submitRequest();
        System.out.println (api.getClass().getName() + " URL [" + api.getURL() + "/" + api.getAPIConfigParameters() + "]");
        System.out.println (api.getLatitude());
        System.out.println (api.getLongitude());
    }
}