package RestaurantV3;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface that defines how subsequent restaurant api clases should be defined.
 * @author Diego Rodriguez 
 * Updated: 2/27/2020
 */
public interface RestaurantApiInterface {
    
    public ArrayList<Map> loadCuisineListByLocation(double _lat, double _lon);
    
    public ArrayList<Map> loadRestaurantListByID(int _cuisineID, double _lat , double _lon);
}
