package RestaurantV3;

/**
 * Interface that defines how subsequent restaurant api clases should be defined.
 * @author Diego Rodriguez 
 * Updated: 2/27/2020
 */
public interface RestaurantApiInterface {
    
    public String loadCusineListByLocation(double _lat, double _lon);
    
    public String loadRestaurantListByID(int _cuisineID, double _lat , double _lon);
}
