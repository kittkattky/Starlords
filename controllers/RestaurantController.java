package controllers;

/**
 * Restaurant Controller for handling flow of information to/from view and model.
 * @author Diego Rodriguez
 * Last Updated: 4/11/2020
 */

import models.RestaurantModel;
import java.util.Map;

public class RestaurantController {

    protected RestaurantModel restaurantModel = new RestaurantModel();
    protected int cuisineID;
    protected String uuid;

    //=================  GETTERS ===============
    
    public int getCuisineID() {
        return this.cuisineID;
    }
    
    public Map getCuisineMap() throws Exception {
        this.restaurantModel = restaurantModel.loadCuisinesByLocation(this.uuid);
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
    public Map getRestaurantNameMap() throws Exception {
        this.restaurantModel = restaurantModel.loadRestaurantsByID(this.cuisineID, this.uuid);
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
    
    public String getUUID() {
        return this.uuid;
    }
    //=================  SETTERS ===============
    public void setCuisineID(int _cuisineID) {
        this.cuisineID = _cuisineID;
    }
    
    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }
        
}
