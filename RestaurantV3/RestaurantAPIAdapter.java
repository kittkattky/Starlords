package RestaurantV3;

/**
 * Adapter class, tells model which translator/api to use.
 *
 * @author Diego Rodriguez Updated: 2/27/2020
 */
public class RestaurantAPIAdapter implements RestaurantApiInterface {

    //the Zomato api is the only restaurant api that is currently being used for this project
    protected static final RestaurantApiInterface currentlyUsedApi = new ZomatoApi();

    @Override
    public String loadCusineListByLocation(double _lat, double _lon) {
        return RestaurantAPIAdapter.currentlyUsedApi.loadCusineListByLocation(_lat, _lon);
    }

    @Override
    public String loadRestaurantListByID(int _cuisineID, double _lat, double _lon) {
        return RestaurantAPIAdapter.currentlyUsedApi.loadRestaurantListByID(_cuisineID, _lat, _lon);
    }

}
