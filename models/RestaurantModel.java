package models;

/**
 * Handles calls
 *
 * @author Diego Rodriguez Updated: 3/30/2020
 */
import api.adapters.RestaurantAPIAdapter;
import java.util.ArrayList;
import java.util.Map;

public class RestaurantModel {

    //varaibles for setters and getters
    protected double lat;
    protected double lon;
    protected int cuisineID;
    protected Map cuisineMap;
    protected Map cuisineIDMap;
    protected Map restaurantNameMap;
    protected Map restaurantUrlMap;
    protected Map restaurantAddressMap;
    protected Map restaurantRatingMap;

    //constants that reference the array list returned by adapter methods.
    private static final int cuisineMapINDEX = 0;
    private static final int cuisineIDMapINDEX = 1;
    private static final int restaurantNameMapINDEX = 0;
    private static final int restaurantUrlMapINDEX = 1;
    private static final int restaurantAddressMapINDEX = 2;
    private static final int restaurantRatingMapINDEX = 3;

    
    
    protected final static RestaurantAPIAdapter adapter = new RestaurantAPIAdapter();

    /**
     * Returns a RestaurantModel object whose instance has a set latitude,
     * longitude, and cuisine list.
     *
     * @param _lat
     * @param _lon
     * @return
     */
    public static RestaurantModel loadCuisinesByLocation(double _lat, double _lon) {
        RestaurantModel useModel = new RestaurantModel();
        ArrayList<Map> tempStorageForMaps = adapter.loadCuisineListByLocation(_lat, _lon);
        
        useModel.setLat(_lat);
        useModel.setLon(_lon);
        
        Map cuisineMap = tempStorageForMaps.get(cuisineMapINDEX);
        useModel.setCuisineMap(cuisineMap);

        Map cuisineIDMap = tempStorageForMaps.get(cuisineIDMapINDEX);
        useModel.setCuisineIDMap(cuisineIDMap);

        return useModel;
    }

    /**
     * Returns a RestaurantModel object whose instance has a set cuisine ID,
     * latitude, longitude, and restaurant list.
     *
     * @param _id
     * @param _lat
     * @param _lon
     * @return
     */
    public static RestaurantModel loadRestaurantsByID(int _id, double _lat, double _lon) {
        RestaurantModel useModel = new RestaurantModel();
        ArrayList<Map> tempStorageForMaps = adapter.loadRestaurantListByID(_id, _lat, _lon);
        
        useModel.setLat(_lat);
        useModel.setLon(_lon);

        Map restaurantNameMap = tempStorageForMaps.get(restaurantNameMapINDEX);
        useModel.setRestaurantNameMap(restaurantNameMap);

        Map restaurantUrlMap = tempStorageForMaps.get(restaurantUrlMapINDEX);
        useModel.setRestaurantUrlMap(restaurantUrlMap);

        Map restaurantAddressMap = tempStorageForMaps.get(restaurantAddressMapINDEX);
        useModel.setRestaurantAddressMap(restaurantAddressMap);

        Map restaurantRatingMap = tempStorageForMaps.get(restaurantRatingMapINDEX);
        useModel.setRestaurantRatingMap(restaurantRatingMap);

        return useModel;
    }

    //=================  GETTERS ===============
    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public int getCuisineID() {
        return this.cuisineID;
    }

    public Map getCuisineMap() {
        return this.cuisineMap;
    }

    public Map getCuisineIDMap() {
        return this.cuisineIDMap;
    }

    public Map getRestaurantNameMap() {
        return this.restaurantNameMap;
    }

    public Map getRestaurantUrlMap() {
        return this.restaurantUrlMap;
    }

    public Map getRestaurantAddressMap() {
        return this.restaurantAddressMap;
    }

    public Map getRestaurantRatingMap() {
        return this.restaurantRatingMap;
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

    public void setCuisineMap(Map _cuisineMap) {
        this.cuisineMap = _cuisineMap;
    }

    public void setCuisineIDMap(Map _cuisineIDMap) {
        this.cuisineIDMap = _cuisineIDMap;
    }

    public void setRestaurantNameMap(Map _restaurantNameMap) {
        this.restaurantNameMap = _restaurantNameMap;
    }

    public void setRestaurantUrlMap(Map _restaurantUrlMap) {
        this.restaurantUrlMap = _restaurantUrlMap;
    }

    public void setRestaurantAddressMap(Map _restaurantAddressMap) {
        this.restaurantAddressMap = _restaurantAddressMap;
    }

    public void setRestaurantRatingMap(Map _restaurantRatingMap) {
        this.restaurantRatingMap = _restaurantRatingMap;
    }

}
