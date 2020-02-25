/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestaurantV2;

import org.json.JSONException;

/**
 *
 * @author darod
 */
public class Driver {
    public static void main(String[] args) {
        
        RestaurantAPIModel restaurantModel = new RestaurantAPIModel();
        ZomatoAPI api = new ZomatoAPI(restaurantModel);
        RestaurantAPIController restaurantController = new RestaurantAPIController(restaurantModel, api);
        
        restaurantController.setAPIConfigParameter("getRequestType", "cuisines?");
        restaurantController.setAPIConfigParameter("latitude", "lat=36.066984");
        restaurantController.setAPIConfigParameter("longitude", "&lon=-79.800178");
        
        try{
            
          restaurantController.getCuisineList();
          
        }catch(JSONException ex){
            System.out.println("something went wrong..");
        }
        
        
    }
}
