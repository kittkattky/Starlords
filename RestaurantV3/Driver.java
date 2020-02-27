package RestaurantV3;

/**
 * Basic test driver to show that methods work, and information flow is correct.
 *
 * @author Diego Rodriguez
 */
public class Driver {

    public static void main(String[] args) {
        double lat = 36.066984;
        double lon = -79.800178;
        int cuisineID = 1;
        RestaurantModel modelA = new RestaurantModel();
        RestaurantModel modelB = new RestaurantModel();

        modelA = modelA.loadCusinesByLocation(lat, lon);
        System.out.println(modelA.getCuisineList());

        modelB = modelB.loadRestaurantsByID(cuisineID, lat, lon);
        System.out.println(modelB.getRestaurantList());

    }

}
