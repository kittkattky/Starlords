package RestaurantV3;

/**
 * Handles calls
 *
 * @author Diego Rodriguez Updated: 2/27/2020
 */
public class RestaurantModel {
    //varaibles for setters and getters
    protected double lat;
    protected double lon;
    protected int cuisineID;
    protected String cuisineList;
    protected String restaurantList;
    
    protected final static RestaurantAPIAdapter adapter = new RestaurantAPIAdapter();

    /**
     * Returns a RestaurantModel object whose instance has a set latitude, longitude, and cuisine list. 
     * @param _lat
     * @param _lon
     * @return 
     */
    public static RestaurantModel loadCusinesByLocation(double _lat, double _lon) {
        RestaurantModel useModel = new RestaurantModel();
        useModel.setLat(_lat);
        useModel.setLon(_lon);
        String cuisineList = adapter.loadCusineListByLocation(_lat, _lon);
        useModel.setCuisineList(cuisineList);

        return useModel;
    }

    /**
     * Returns a RestaurantModel object whose instance has a set cuisine ID, latitude, longitude, and restaurant list. 
     * @param _id
     * @param _lat
     * @param _lon
     * @return 
     */
    public static RestaurantModel loadRestaurantsByID(int _id, double _lat, double _lon) {
        RestaurantModel useModel = new RestaurantModel();
        useModel.setID(_id);
        useModel.setLat(_lat);
        useModel.setLon(_lon);
        String restaurantList = adapter.loadRestaurantListByID(_id, _lat, _lon);
        useModel.setRestaurantList(restaurantList);

        return useModel;
    }

    //=================  GETTERS ===============
    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public int getCusineID() {
        return this.cuisineID;
    }

    public String getCuisineList() {
        return this.cuisineList;
    }

    public String getRestaurantList() {
        return this.restaurantList;
    }

    //=================  SETTERS ===============
    public void setLat(double _lat) {
        this.lat = _lat;
    }

    public void setLon(double _lon) {
        this.lon = _lon;
    }

    public void setID(int _id) {
        this.cuisineID = _id;
    }

    public void setCuisineList(String _cuisineList) {
        this.cuisineList = _cuisineList;
    }

    public void setRestaurantList(String _restaurantList) {
        this.restaurantList = _restaurantList;
    }

}
