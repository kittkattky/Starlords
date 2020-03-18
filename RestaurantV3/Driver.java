package RestaurantV3;

import LocationAPI.*;
import java.lang.reflect.*;

/**
 * Basic test driver to show that methods work, and information flow is correct.
 *
 * @author Diego Rodriguez
 */
public class Driver {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        LocationAPI api = new GeoLocation ();

        /*
        api.submitRequest();
        
        double lat = api.getLatitude();
        double lon = api.getLongitude();
        
        System.out.println ("latitude: " + lat);
        System.out.println ("longitude: " + lon);
        
        int cuisineID = 1;
        RestaurantModel modelA = new RestaurantModel();
        RestaurantModel modelB = new RestaurantModel();

        modelA = modelA.loadCusinesByLocation(lat, lon);
        System.out.println(modelA.getCuisineList());

        modelB = modelB.loadRestaurantsByID(cuisineID, lat, lon);
        System.out.println(modelB.getRestaurantList());*/

    }

}
