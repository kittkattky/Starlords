package RestaurantV2;

/**
 * RestaurantAPIController class that extends APIController class.
 * Handles gets/sets specific to Restaurant model.
 * @author Diego Rodriguez
 * Updated: 2/25/2019
 */

import ControllerModel.*;
import org.json.*;

public class RestaurantAPIController extends APIController{
    
    protected RestaurantAPIModel restaurantModel;
    protected ZomatoAPI zomatoAPI;
    
    /**
     * @param _model
     * @param _api
     * Constructor calls super class constructor and sets 2 other objects specific to the subclass.
     */
    public RestaurantAPIController (RestaurantAPIModel _model, ZomatoAPI _api){
        super(_model);
        this.restaurantModel = _model;
        this.zomatoAPI = _api;
    }

    //=================  GETTERS ===============

    //gets a list of cuisines from the restaurant model
    public void getCuisineList() throws JSONException{
        this.restaurantModel.printCuisineList(this.zomatoAPI.retrieveJSONData());
    }

    //gets a list of restaurants from the restaurant model
    public void getRestaurantList() throws JSONException{
        this.restaurantModel.printRestaurantList(this.zomatoAPI.retrieveJSONData());
    }

}
