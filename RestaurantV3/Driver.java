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
        int cuisineID = 73;

       
        RestaurantModel modelA = new RestaurantModel();
        RestaurantModel modelB = new RestaurantModel();

        modelA = modelA.loadCuisinesByLocation(lat, lon);
        System.out.println(modelA.getCuisineMap());

        modelB = modelA.loadRestaurantsByID(959, lat, lon);
        System.out.println(modelB.getRestaurantNameMap());
        System.out.println(modelB.getRestaurantUrlMap());
        System.out.println(modelB.getRestaurantAddressMap());
        System.out.println(modelB.getRestaurantRatingMap());
    }

}
