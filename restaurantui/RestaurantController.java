package restaurantui;

/**
 * Restaurant Controller for handling all requests from views and communicating with the Restaurant Model.
 * @author Diego Rodriguez
 * Last Updated: 4/4/2020
 */

import RestaurantV3.RestaurantModel;
import java.util.Map;

public class RestaurantController {
    
    private final double LAT = 36.066984;
    private final double LON = -79.800178;
    protected RestaurantModel restaurantModel = new RestaurantModel();
    protected int cuisineID;
   

    //=================  GETTERS ===============
    
    public int getCuisineID() {
        return this.cuisineID;
    }
    
    public Map getCuisineMap() {
        this.restaurantModel = restaurantModel.loadCuisinesByLocation(this.LAT, this.LON);
        return restaurantModel.getCuisineMap();
    }
    
    //assume that getCuisineMap has already been called by CuisineView.
    public Map getCuisineIDMap() {
        return this.restaurantModel.getCuisineIDMap();
    }
    
    /**
     * The get methods will always be called in a specific order, with getRestaurantNameMap being first.
     * So with this in mind, we are going to load the information needed from the API during this call.
     * Any other get methods that need this info will have it from this reference of restaurantModel.
     * @return 
     */
    public Map getRestaurantNameMap() {
        this.restaurantModel = restaurantModel.loadRestaurantsByID(this.cuisineID, this.LAT, this.LON);
        return this.restaurantModel.getRestaurantNameMap();
    }
    
    //assume that getRestaurantNameMap has already been called by RestaurantListView
    public Map getRestaurantURLMap() {
        return this.restaurantModel.getRestaurantUrlMap();
    }
    
    //assume that getRestaurantNameMap has already been called by RestaurantListView
    public Map getRestaurantAddressMap() {
        return this.restaurantModel.getRestaurantAddressMap();
    }
    
    //assume that getRestaurantNameMap has already been called by RestaurantListView
    public Map getRestaurantRatingMap() {
        return this.restaurantModel.getRestaurantRatingMap();
    }
    //=================  SETTERS ===============
    public void setCuisineID(int _cuisineID) {
        this.cuisineID = _cuisineID;
    }
    
    
        
}
