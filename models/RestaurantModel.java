package models;

/**
 * Handles calls to zomato api, also uses location util class to get lon/lat
 * based on zipcode, and uses DatabaseAdapter to get zip code based on uuid.
 *
 * @author Diego Rodriguez Updated: 4/11/2020
 */
import api.adapters.DatabaseAdapter;
import api.translators.LocationAPITranslator;
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
    
    

    protected final static RestaurantAPIAdapter rAdapter = new RestaurantAPIAdapter();
    protected final static DatabaseAdapter dbAdapter = new DatabaseAdapter();
    
    /**
     * Returns a RestaurantModel object whose instance has a set latitude,
     * longitude, and cuisine list.
     *
     * @param _uuid
     * @return
     * @throws java.lang.Exception
     */
    public static RestaurantModel loadCuisinesByLocation(String _uuid) throws Exception {
        //use location util class to get lat/lon based on zip code
        LocationAPITranslator location = new LocationAPITranslator(_uuid, LocationAPITranslator.GEO_CODE);
        String zipCode = dbAdapter.queryForAttribute(_uuid, "zipcode");
        location.setAPIConfigParameter("address", zipCode);
        location.submitRequest();

        RestaurantModel useModel = new RestaurantModel();
        ArrayList<Map> tempStorageForMaps = rAdapter.loadCuisineListByLocation(location.getLatitude(), location.getLongitude());

        useModel.setLat(location.getLatitude());
        useModel.setLon(location.getLongitude());

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
     * @param _uuid
     * @return
     * @throws java.lang.Exception
     */
    public static RestaurantModel loadRestaurantsByID(int _id, String _uuid) throws Exception {
        //use location util class to get lat/lon based on zip code
        LocationAPITranslator location = new LocationAPITranslator(_uuid, LocationAPITranslator.GEO_CODE);
        String zipCode = dbAdapter.queryForAttribute(_uuid, "zipcode");
        
        location.setAPIConfigParameter("address", zipCode);
        location.submitRequest();

        RestaurantModel useModel = new RestaurantModel();
        ArrayList<Map> tempStorageForMaps = rAdapter.loadRestaurantListByID(_id, location.getLatitude(), location.getLongitude());

        useModel.setLat(location.getLatitude());
        useModel.setLon(location.getLongitude());

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