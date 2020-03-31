package RestaurantV3;

/**
 * Interface that defines how subsequent restaurant api clases should be defined.
 * @author Diego Rodriguez 
 * Updated: 3/30/2020
 */

import java.util.ArrayList;
import java.util.Map;

public interface RestaurantApiInterface {
    
    public ArrayList<Map> loadCuisineListByLocation(double _lat, double _lon);
    
    public ArrayList<Map> loadRestaurantListByID(int _cuisineID, double _lat , double _lon);
}
